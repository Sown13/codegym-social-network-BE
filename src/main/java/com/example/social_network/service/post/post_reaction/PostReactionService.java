package com.example.social_network.service.post.post_reaction;

import com.example.social_network.model.post.PostReaction;
import com.example.social_network.repo.post.IPostReactionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PostReactionService implements IPostReactionService{
    @Autowired
    private IPostReactionRepo IPostReactionRepo;

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
}
