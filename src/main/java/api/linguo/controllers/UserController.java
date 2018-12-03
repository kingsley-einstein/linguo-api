package api.linguo.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import org.bson.types.ObjectId;

import api.linguo.repositories.UserRepository;
import api.linguo.models.User;
import api.linguo.exceptions.*;

import java.util.Map;

@RestController
@RequestMapping(value = "/api/v1")
public class UserController {

    @Autowired
    private UserRepository repo;

    @Autowired
    private BCryptPasswordEncoder encoder;

    @PostMapping(value = "/users")
    @ResponseBody
    public User createUser(@RequestBody Map<String, String> body) {
        
        User user = new User(body.get("username"), body.get("email"), encoder.encode(body.get("password")));

        return repo.save(user);
    }

    @PostMapping(value = "/login")
    @ResponseBody
    public User getUser(@RequestBody Map<String, String> body) {
        User user = repo.findByUsername(body.get("username"))
                        .orElseThrow(() -> new UserNotFoundException(String.format("User with username, %s not found", body.get("username"))));
        
        if (encoder.matches(body.get("password"), user.getPassword())) {
            return user;
        }
        else {
            throw new IncorrectPasswordException("Password not correct");
        }
    }

    @GetMapping(value ="/users")
    @ResponseBody
    public Page<User> getAllUsers(@RequestParam("page") Integer page) {
        Page<User> users = repo.findAll(PageRequest.of(page, 12));

        return users;
    }

    @PutMapping(value = "/users/{id}")
    public void editUser(@PathVariable("id") ObjectId id, @RequestBody Map<String, String> body) {
        User user = repo.findById(id)
                        .orElseThrow(() -> new UserNotFoundException("User not found"));

        user.setEmail(body.get("email"));
        user.setPassword(encoder.encode(body.get("password")));

        repo.save(user);
    }
}
