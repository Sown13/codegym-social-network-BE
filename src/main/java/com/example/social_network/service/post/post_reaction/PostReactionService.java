package com.example.social_network.service.post.post_reaction;

import com.example.social_network.model.post.Post;
import com.example.social_network.model.post.PostReaction;
import com.example.social_network.repo.post.IPostReactionRepo;
import com.example.social_network.repo.post.IPostRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PostReactionService implements IPostReactionService{
    @Autowired
    private IPostReactionRepo postReactionRepo;

    @Autowired
    private IPostRepo postRepo;

    @Override
    public Iterable<PostReaction> findAll() {
        return null;
    }

    @Override
    public Optional<PostReaction> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public PostReaction save(PostReaction postReaction) throws Exception {
        return null;
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
}
