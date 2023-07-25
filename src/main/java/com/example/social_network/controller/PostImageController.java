package com.example.social_network.controller;

import com.example.social_network.service.post.post_imge.IPostImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("/post-images")
public class PostImageController {
    @Autowired
    private IPostImageService postImageService;

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePostImage(@PathVariable("id") Long id) {
        postImageService.remove(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
