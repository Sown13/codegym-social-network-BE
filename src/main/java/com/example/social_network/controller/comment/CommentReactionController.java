package com.example.social_network.controller.comment;

import com.example.social_network.model.comment.Comment;
import com.example.social_network.model.comment.CommentReaction;
import com.example.social_network.model.user.User;
import com.example.social_network.service.comment.comment_reaction.ICommentReactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/comment-reactions")
public class CommentReactionController {

    @Autowired
    ICommentReactionService iCommentReactionService;

    @PostMapping("/addCommentReaction/comment/{commentId}/user/{userId}")
    public ResponseEntity<List<CommentReaction>> addLikeComment(@PathVariable Long commentId, @PathVariable Long userId) throws Exception {


        List<CommentReaction> commentReactionList = iCommentReactionService.findByCommentReactionCommentIdAndUserId(commentId, userId);
        if (!commentReactionList.isEmpty()) {
            return new ResponseEntity<>(commentReactionList, HttpStatus.CREATED);
        } else {

            Comment comment = new Comment();
            comment.setCommentId(commentId);

            User user = new User();
            user.setUserId(userId);

            CommentReaction commentReaction1 = new CommentReaction();

            commentReaction1.setComment(comment);
            commentReaction1.setUser(user);

            LocalDateTime now = LocalDateTime.now();
            Date dateCreated = Date.from(now.atZone(ZoneId.systemDefault()).toInstant());
            commentReaction1.setDateCreated(dateCreated);

            commentReaction1 = iCommentReactionService.save(commentReaction1);

            return new ResponseEntity<>(Collections.singletonList(commentReaction1), HttpStatus.OK);
        }
    }

    @PostMapping("/delete/comment/{commentId}/user/{userId}")
    public ResponseEntity<List<CommentReaction>> deleteLikeComment(@PathVariable Long commentId, @PathVariable Long userId) {
        List<CommentReaction> commentReactionList = iCommentReactionService.deleteCommentReactionByCommentIdAndUserID(commentId, userId);
            return new ResponseEntity<>(commentReactionList, HttpStatus.OK);


    }

}
