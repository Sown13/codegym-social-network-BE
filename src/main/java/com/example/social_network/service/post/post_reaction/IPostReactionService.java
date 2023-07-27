package com.example.social_network.service.post.post_reaction;

import com.example.social_network.dto.dto.PostReactionDTO;
import com.example.social_network.model.post.PostReaction;
import com.example.social_network.service.IGeneralService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface IPostReactionService extends IGeneralService<PostReaction> {
    Long countPostReactionByPostId(Long postId);
    Long countPostReactionByPostIdAndReactionType(Long id,String type);


    List<PostReaction> findByPostIdAndUserId(Long postId, Long userId);

    List<PostReaction> DeleteUserIdAAndPostId( Long postId ,Long userId);

}
