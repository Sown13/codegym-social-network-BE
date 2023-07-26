package com.example.social_network.controller;

import com.example.social_network.model.post.Post;
import com.example.social_network.model.post.PostImage;
import com.example.social_network.service.post.post.IPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("posts")
public class PostController {
    @Autowired
    private IPostService iPostService;

    @GetMapping
    public ResponseEntity<Iterable<Post>> findAllPost() {
        return new ResponseEntity<>(iPostService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<Iterable<Post>> findPostByUserId(@PathVariable Long id) {
        return new ResponseEntity<>(iPostService.findPostsByUserId(id), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Iterable<PostImage>> getImagesByPostId(@PathVariable Long id) {
        return new ResponseEntity<>(iPostService.findImagesByPostId(id), HttpStatus.OK);
    }

    @GetMapping("userSource/{id}")
    public ResponseEntity<Iterable<Post>> findAllPostWhereIsAcceptedTrue(@PathVariable Long id) {
        return new ResponseEntity<>(this.iPostService.findPostsOfAcceptedFriends(id),  HttpStatus.OK);
    }

}
