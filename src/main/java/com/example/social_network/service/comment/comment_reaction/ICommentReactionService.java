package com.example.social_network.service.comment.comment_reaction;

import com.example.social_network.model.comment.CommentReaction;
import com.example.social_network.service.IGeneralService;

import java.util.List;

public interface ICommentReactionService extends IGeneralService<CommentReaction> {

    List<CommentReaction> findByCommentReactionCommentIdAndUserId(Long commentId, Long userId);

    List<CommentReaction> deleteCommentReactionByCommentIdAndUserID(Long commentId , Long userId);


}
