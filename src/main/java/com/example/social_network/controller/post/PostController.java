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
import java.util.List;
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
        Post postOptional = postService.findById(id).orElse(null);
        if (postOptional != null) {
            Date now = new Date();
            postOptional.setDateUpdated(now);
            postOptional.setTextContent(post.getTextContent());
            postService.save(postOptional);
            return new ResponseEntity<>(post, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


    @GetMapping
    public ResponseEntity<Iterable<Post>> findAllPost() {
        return new ResponseEntity<>(postService.findAll(), HttpStatus.OK);
    }
    @GetMapping("/{id}")
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


    @GetMapping("user-source/{id}")
    public ResponseEntity<Iterable<Post>> findAllPostWhereIsAcceptedTrue(@PathVariable Long id) {
        return new ResponseEntity<>(this.postService.findPostsOfAcceptedFriends(id), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePostByPostId(@PathVariable Long id) {
        postService.remove(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }


    @PutMapping("/update-authorized-view/{postId}/authorizedView/{authorizedView}")
    public ResponseEntity<Post> updateAuthorizedView(@PathVariable Long postId, @PathVariable String authorizedView) {
        try {
            Post updatedPost = postService.updateAuthorizedViewByPostId(postId, authorizedView);
            return ResponseEntity.ok(updatedPost);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }


}