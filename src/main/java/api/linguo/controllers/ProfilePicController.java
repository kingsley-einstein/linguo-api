package api.linguo.controllers;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.bson.types.ObjectId;


import api.linguo.models.ProfilePic;
import api.linguo.models.User;
import api.linguo.repositories.*;
import api.linguo.exceptions.InvalidMimeTypeException;

@RestController
@RequestMapping(value = "/api/v1")
public class ProfilePicController {

    @Autowired
    private ProfilePicRepository picRepo;

    @Autowired
    private UserRepository userRepo;

    @PostMapping(value = "/pictures")
    @ResponseBody
    public String editOrUpload(@RequestParam("owner") ObjectId id, @RequestParam("file") MultipartFile file) throws Exception {
        ProfilePic pic = picRepo.findByOwner(userRepo.findById(id).get());
        User user = userRepo.findById(id).get();

        if (pic != null) {
           if (file.getContentType().contains("image")) {
               pic.setData(file.getBytes());
               pic.setMimeType(file.getContentType());
               user.setProfilePic(pic);
               picRepo.save(pic);
               userRepo.save(user);
           }
           else {
               throw new InvalidMimeTypeException(String.format("Invalid file type. Expected image but found %s", file.getContentType()));
           }
        }
        else {
            pic = new ProfilePic(file.getContentType(), file.getBytes(), userRepo.findById(id).get());
            if (pic.getMimeType().contains("image")) {
                user.setProfilePic(pic);
                picRepo.save(pic);
                userRepo.save(user);
            }
            else {
                throw new InvalidMimeTypeException(String.format("Invalid file type. Expected image but found %s", pic.getMimeType()));
            }
        }

        return "Picture successfully uploaded";
    }

    @DeleteMapping(value = "/pictures")
    @ResponseBody
    public String deletePic(@RequestParam("id") ObjectId id) {
        picRepo.deleteById(id);

        return "Picture successfully deleted";
    }
}