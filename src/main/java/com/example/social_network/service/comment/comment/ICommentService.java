package com.example.social_network.service.comment.comment;

import com.example.social_network.model.comment.Comment;
import com.example.social_network.service.IGeneralService;

import java.util.List;

public interface ICommentService extends IGeneralService<Comment> {
    List<Comment>getAllCommentsByPostId(Long id);
}
