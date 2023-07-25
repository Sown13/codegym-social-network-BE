package com.example.social_network.controller;

import com.example.social_network.service.post.post_imge.IPostImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin("*")
@RequestMapping("/post-images")
public class PostImageController {
    @Autowired
    private IPostImageService postImageService;
}
