package com.flesk.messageriee.controllers;

import com.flesk.messageriee.models.Image;
import com.flesk.messageriee.models.User;
import com.flesk.messageriee.services.CloudinaryService;
import com.flesk.messageriee.services.ImageService;
import com.flesk.messageriee.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/cloudinary")
@CrossOrigin(origins = "http://localhost:4200")
public class CloudinaryController {
    @Autowired
    CloudinaryService cloudinaryService;

    @Autowired
    ImageService imageService;
    @Autowired
    UserService userService;

    @GetMapping("/list")
    public ResponseEntity<List<Image>> list(){
        List<Image> list = imageService.list();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @PostMapping("/upload/{userId}")
    @ResponseBody
    public ResponseEntity<String> upload(@PathVariable String userId, @RequestParam MultipartFile multipartFile) throws IOException {
        Optional<User> optionalUser = userService.getUserById(userId);
        if (optionalUser.isEmpty()) {
            return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
        }
        User user = optionalUser.get();

        BufferedImage bi = ImageIO.read(multipartFile.getInputStream());
        if (bi == null) {
            return new ResponseEntity<>("Invalid image", HttpStatus.BAD_REQUEST);
        }

        Map result = cloudinaryService.upload(multipartFile);
        Image image = new Image((String) result.get("original_filename"),
                (String) result.get("url"),
                (String) result.get("public_id"));
        imageService.save(image);

        // Associate the uploaded image with the user
        user.setImage(image);
        userService.saveUser(user);

        return new ResponseEntity<>("Image uploaded successfully", HttpStatus.OK);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") String id) {
        Optional<Image> imageOptional = imageService.getOne(id);
        if (imageOptional.isEmpty()) {
            return new ResponseEntity<>("n'existe pas", HttpStatus.NOT_FOUND);
        }
        Image image = imageOptional.get();
        String cloudinaryImageId = image.getImageId();
        try {
            cloudinaryService.delete(cloudinaryImageId);
        } catch (IOException e) {
            return new ResponseEntity<>("Failed to delete image from Cloudinary", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        imageService.delete(id);
        return new ResponseEntity<>("image supprimée !", HttpStatus.OK);
    }

}