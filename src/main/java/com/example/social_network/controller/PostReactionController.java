package com.example.social_network.controller;
import com.example.social_network.service.post.post_reaction.IPostReactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("/post-reactions")
public class PostReactionController {
    @Autowired
    private IPostReactionService postReactionService;

    @GetMapping("/post/{id}/total-like")
    public Long countLikeOfPost(@PathVariable Long id){
        return postReactionService.countPostReactionByPostId(id);
    }
}
