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
import java.util.Optional;

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

    @PutMapping("/{id}")
    public ResponseEntity<?> updatePost(@PathVariable Long id, @RequestBody Post post) throws Exception {
        Optional<Post> postOptional = postService.findById(id);
        if (postOptional.isPresent()) {
            post.setPostId(id);
            Date now = new Date();
            post.setDateUpdated(now);
            postService.save(post);
            return new ResponseEntity<>(post, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


    @GetMapping
    public ResponseEntity<Iterable<Post>> findAllPost() {
        return new ResponseEntity<>(postService.findAll(), HttpStatus.OK);
    }
    @GetMapping("/post/{id}")
    public ResponseEntity<?>findPostById(@PathVariable Long id){
        Optional<Post>postOptional=postService.findById(id);
        if(postOptional.isPresent()){
            return new ResponseEntity<>(postOptional,HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<Iterable<Post>> findPostByUserId(@PathVariable Long id) {
        return new ResponseEntity<>(postService.findPostsByUserId(id), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Iterable<PostImage>> getImagesByPostId(@PathVariable Long id) {
        return new ResponseEntity<>(postService.findImagesByPostId(id), HttpStatus.OK);
    }

    @GetMapping("userSource/{id}")
    public ResponseEntity<Iterable<Post>> findAllPostWhereIsAcceptedTrue(@PathVariable Long id) {
        return new ResponseEntity<>(this.postService.findPostsOfAcceptedFriends(id), HttpStatus.OK);
    }
}
