package com.example.social_network.service.comment.comment_reaction;

import com.example.social_network.model.comment.CommentReaction;
import com.example.social_network.repo.comment.ICommentReactionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CommentReactionService implements ICommentReactionService {

    @Autowired
    ICommentReactionRepo iCommentReactionRepo;

    @Override
    public Iterable<CommentReaction> findAll() {
        return iCommentReactionRepo.findAll();
    }

    @Override
    public Optional<CommentReaction> findById(Long id) {
        return iCommentReactionRepo.findById(id);
    }

    @Override
    public CommentReaction save(CommentReaction commentReaction) throws Exception {
        return iCommentReactionRepo.save(commentReaction);
    }

    @Override
    public void remove(Long id) {
        iCommentReactionRepo.deleteById(id);
    }

    @Override
    public List<CommentReaction> findByCommentReactionCommentIdAndUserId(Long commentId, Long userId) {
        return iCommentReactionRepo.findByCommentReactionCommentIdAndUserId(commentId , userId);
    }

    @Override
    public List<CommentReaction> deleteCommentReactionByCommentIdAndUserID(Long commentId , Long userId) {
        List<CommentReaction> commentReactionList = iCommentReactionRepo.deleteCommentReactionByCommentIdAndUserID(commentId,userId);

        if (!commentReactionList.isEmpty()){
            CommentReaction commentReaction = commentReactionList.get(0);
            iCommentReactionRepo.delete(commentReaction);
        }
        return commentReactionList;
    }

}
