package com.example.social_network.controller.post;

import com.example.social_network.dto.dto.PostReactionDTO;
import com.example.social_network.model.post.Post;
import com.example.social_network.model.post.PostReaction;
import com.example.social_network.model.user.User;
import com.example.social_network.service.post.post_reaction.IPostReactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.Date;
import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/post-reactions")
public class PostReactionController {
    @Autowired
    private IPostReactionService postReactionService;

    @GetMapping("/post/{id}/total-like")
    public Long countLikeOfPost(@PathVariable Long id) {
        return postReactionService.countPostReactionByPostId(id);
    }


    @GetMapping("")
    public ResponseEntity<Iterable<PostReaction>> findAll() {
        Iterable<PostReaction> postReactionIterable = postReactionService.findAll();
        return new ResponseEntity<>(postReactionIterable, HttpStatus.OK);
    }

    // thêm like vào bảng PostReaction
    @PostMapping("/add/post/{postId}/user/{userId}")
    public ResponseEntity<List<PostReaction>> addToLikeBoard(@RequestBody PostReaction postReaction,
                                                                 @PathVariable Long postId,
                                                                 @PathVariable Long userId) throws Exception {
        postReaction.setDateCreated(new Date());

        List<PostReaction> postReactionIterable = postReactionService.findByPostIdAndUserId(postId, userId);
        if (!postReactionIterable.isEmpty()) {
            // If a reaction already exists, return the existing reactions
            return new ResponseEntity<>(postReactionIterable, HttpStatus.CREATED);
        } else {
            // If no reaction exists, add a new PostReaction
            User user = new User();
            user.setUserId(userId);

            Post post = new Post();
            post.setPostId(postId);

            PostReaction newPostReaction = new PostReaction();

            newPostReaction.setUser(user);
            newPostReaction.setPost(post);

            newPostReaction = postReactionService.save(newPostReaction);

            return new ResponseEntity<>(Collections.singletonList(newPostReaction), HttpStatus.OK);
        }
    }

    // nếu userId and postId mà có ở trong bảng thì xóa nếu null thì thêm
    @PostMapping("/deleteAndAdd/post/{postId}/user/{userId}")
    public ResponseEntity<List<PostReaction>> deleteStatus(@PathVariable Long postId, @PathVariable Long userId) throws Exception {
        List<PostReaction> postReactions = postReactionService.DeleteUserIdAAndPostId(postId, userId);

        if (!postReactions.isEmpty()) {
            return new ResponseEntity<>(postReactions, HttpStatus.OK);
        }

            return null;

    }

}



