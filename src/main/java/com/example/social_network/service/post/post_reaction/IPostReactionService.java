package com.example.social_network.service.post.post_reaction;

import com.example.social_network.dto.dto.PostReactionDTO;
import com.example.social_network.model.post.PostReaction;
import com.example.social_network.service.IGeneralService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface IPostReactionService extends IGeneralService<PostReaction> {


    List<PostReactionDTO> findByPostIdAndUserId(Long postId , Long userId);

    List<PostReaction> findByUserIdAAndPostId(Long userId, Long postId);

}
