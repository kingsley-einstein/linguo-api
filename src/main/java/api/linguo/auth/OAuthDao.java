package api.linguo.auth;

import org.springframework.stereotype.Repository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.beans.factory.annotation.Autowired;

import api.linguo.models.User;
import api.linguo.repositories.UserRepository;
import api.linguo.exceptions.UserNotFoundException;

import java.util.ArrayList;

@Repository
public class OAuthDao {

    @Autowired
    private UserRepository userRepo;

    public User loadUserByUsername(String username) {
        User user = userRepo.findByUsername(username)
                            .orElseThrow(() -> new UserNotFoundException("User not found in database"));

        user.setGrantedAuthoritiesList(new ArrayList<>());
        user.addAuthority(new SimpleGrantedAuthority("user"));

        return user;
    }
}