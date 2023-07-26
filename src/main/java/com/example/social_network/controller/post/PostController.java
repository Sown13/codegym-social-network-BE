package com.example.social_network.controller.post;

import com.example.social_network.model.post.Post;
import com.example.social_network.model.post.PostImage;
import com.example.social_network.service.post.post.IPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Date;

@RestController
@CrossOrigin("*")
@RequestMapping("/posts")
public class PostController {
    @Autowired
    private IPostService postService;
    @PostMapping("")
    private ResponseEntity<?> createPost(@RequestBody Post post) throws Exception {
        Date now = new Date();
        post.setDateCreated(now);
        return new ResponseEntity<>(postService.save(post), HttpStatus.OK);
    }
    @GetMapping
    public ResponseEntity<Iterable<Post>> findAllPost() {
        return new ResponseEntity<>(postService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<Iterable<Post>> findPostByUserId(@PathVariable Long id) {
        return new ResponseEntity<>(postService.findPostsByUserId(id), HttpStatus.OK);
    }



    @GetMapping("userSource/{id}")
    public ResponseEntity<Iterable<Post>> findAllPostWhereIsAcceptedTrue(@PathVariable Long id) {
        return new ResponseEntity<>(this.postService.findPostsOfAcceptedFriends(id),  HttpStatus.OK);
    }
}
