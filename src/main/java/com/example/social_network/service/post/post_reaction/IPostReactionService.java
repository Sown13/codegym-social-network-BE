package com.example.social_network.service.post.post_reaction;

import com.example.social_network.model.post.PostReaction;
import com.example.social_network.service.IGeneralService;
import org.springframework.stereotype.Service;

@Service
public interface IPostReactionService extends IGeneralService<PostReaction> {
    Long countPostReactionByPostId(Long postId);
    Long countPostReactionByPostIdAndReactionType(Long id,String type);
}
