package com.example.social_network.service.comment.comment_reaction;

import com.example.social_network.model.comment.CommentReaction;
import com.example.social_network.service.comment.comment.ICommentService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CommentReactionService implements ICommentReactionService {
    @Override
    public Iterable<CommentReaction> findAll() {
        return null;
    }

    @Override
    public Optional<CommentReaction> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public CommentReaction save(CommentReaction commentReaction) throws Exception {
        return null;
    }

    @Override
    public void remove(Long id) {

    }
}
