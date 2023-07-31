package com.example.social_network.service.comment.comment;

import com.example.social_network.model.comment.Comment;
import com.example.social_network.repo.comment.ICommentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CommentService implements ICommentService{
    @Autowired
    private ICommentRepo commentRepo;
    @Override
    public Iterable<Comment> findAll() {
        return commentRepo.findAll();

    }

    @Override
    public Optional<Comment> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public Comment save(Comment comment) throws Exception {
        return commentRepo.save(comment);
    }

    @Override
    public void remove(Long id) {

    }

    @Override
    public List<Comment> getAllCommentsByPostId(Long id) {
        return commentRepo.getAllCommentsByPostId(id);
    }
}
