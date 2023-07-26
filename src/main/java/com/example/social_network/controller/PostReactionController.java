package com.example.social_network.controller;

import com.example.social_network.dto.dto.PostReactionDTO;
import com.example.social_network.model.post.Post;
import com.example.social_network.model.post.PostReaction;
import com.example.social_network.model.user.User;
import com.example.social_network.service.post.post_reaction.IPostReactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/post-reactions")
public class PostReactionController {
    @Autowired
    private IPostReactionService postReactionService;

    @GetMapping("")
    public ResponseEntity<Iterable<PostReaction>> findAll() {
        Iterable<PostReaction> postReactionIterable = postReactionService.findAll();
        return new ResponseEntity<>(postReactionIterable, HttpStatus.OK);
    }

    // thêm like vào bảng PostReaction
    @PostMapping("/post/{postId}/user/{userId}")
    public ResponseEntity<Iterable<PostReactionDTO>> addToLikeBoard(@RequestBody PostReaction postReaction,
                                                                    @PathVariable Long postId,
                                                                    @PathVariable Long userId) {
        postReaction.setDateCreated(new Date());

        Iterable<PostReactionDTO> postReactionIterable = postReactionService.findByPostIdAndUserId(postId, userId);

        if (postReactionIterable == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(postReactionIterable, HttpStatus.CREATED);
    }

// nếu userId and postId mà có ở trong bảng thì xóa nếu null thì thêm
    @PostMapping("/user/{userId}/post/{postId}")
    public ResponseEntity<PostReaction> deleteStatus(@PathVariable Long userId , @PathVariable Long postId) throws Exception {
        List<PostReaction> postReactions = postReactionService.findByUserIdAAndPostId(userId, postId);

        if (!postReactions.isEmpty()) {
            return new ResponseEntity<>(postReactions.get(0), HttpStatus.OK);
        } else {
            User user = new User();
            user.setUserId(userId);

            Post post = new Post();
            post.setPostId(postId);

            PostReaction newPostReaction = new PostReaction();

            newPostReaction.setUser(user);
            newPostReaction.setPost(post);

            PostReaction savedPostReaction = postReactionService.save(newPostReaction);

            return new ResponseEntity<>(savedPostReaction, HttpStatus.OK);
        }
    }
    
}



