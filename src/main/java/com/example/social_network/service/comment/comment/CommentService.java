package com.example.social_network.service.comment.comment;

import com.example.social_network.model.comment.Comment;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CommentService implements ICommentService{
    @Override
    public Iterable<Comment> findAll() {
        return null;
    }

    @Override
    public Optional<Comment> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public Comment save(Comment comment) throws Exception {
        return null;
    }

    @Override
    public void remove(Long id) {

    }
}
