package com.example.social_network.service.post.post_reaction;

import com.example.social_network.dto.dto.PostReactionDTO;
import com.example.social_network.model.post.Post;
import com.example.social_network.model.post.PostReaction;
import com.example.social_network.repo.post.IPostReactionRepo;
import com.example.social_network.repo.post.IPostRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class PostReactionService implements IPostReactionService{
    @Autowired
    private IPostReactionRepo postReactionRepo;

    @Autowired
    private IPostRepo postRepo;

    @Override
    public Iterable<PostReaction> findAll() {
        return postReactionRepo.findAll();
    }

    @Override
    public Optional<PostReaction> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public PostReaction save(PostReaction postReaction) throws Exception {
        return postReactionRepo.save(postReaction);
    }

    @Override
    public void remove(Long id) {

    }

    @Override
    public Long countPostReactionByPostId(Long postId) {
        Post post = postRepo.findById(postId).orElse(null);
        return postReactionRepo.countPostReactionsByPost(post);
    }

    @Override
    public Long countPostReactionByPostIdAndReactionType(Long postId, String type) {
        Post post = postRepo.findById(postId).orElse(null);
        return postReactionRepo.countPostReactionByPostAndReactionType(post, type);
    }

    @Override
    public List<PostReaction> findByPostIdAndUserId(Long postId, Long userId) {
        return postReactionRepo.findByPostIdAndUserId(postId , userId);
    }

    @Override
    public List<PostReaction> DeleteUserIdAAndPostId(Long postId, Long userId) {
        List<PostReaction> postReactions = postReactionRepo.DeleteUserIdAAndPostId(postId ,userId );

        if (!postReactions.isEmpty()) {
            // Nếu tìm thấy phản ứng, xóa nó
            PostReaction postReaction = postReactions.get(0);
            postReactionRepo.delete(postReaction);
        }

        return postReactions;
    }


//    @Override
//    public List<PostReactionDTO> findByPostIdAndUserId(Long postId, Long userId) {
//        List<Object[]> postReactions = postReactionRepo.findByPostIdAndUserId(postId, userId);
//        List<PostReactionDTO> postReactionDTOs = new ArrayList<>();
//        for (Object[] postReaction : postReactions) {
//            Long reactionId = (Long) postReaction[0];
//            Date dateCreated = (Date) postReaction[1];
//            Long postPostId = (Long) postReaction[2];
//            Long userUserId = (Long) postReaction[3];
//            String accountName = (String) postReaction[4];
//            String reactionType = (String) postReaction[5];
//            postReactionDTOs.add(new PostReactionDTO(reactionId, dateCreated, postPostId, userUserId, accountName, reactionType));
//        }
//
//
//        return postReactionDTOs;
//    }





}
