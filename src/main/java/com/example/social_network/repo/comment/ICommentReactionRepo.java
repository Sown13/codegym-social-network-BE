package com.example.social_network.repo.comment;

import com.example.social_network.model.comment.CommentReaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ICommentReactionRepo extends JpaRepository<CommentReaction, Long> {

    @Query(value = "SELECT * FROM comment_reaction \n" +
            "WHERE comment_reaction.comment_comment_id = :commentId AND comment_reaction.user_user_id = :userId" , nativeQuery = true)
    List<CommentReaction> findByCommentReactionCommentIdAndUserId(Long commentId, Long userId);

    @Query(value = "select * from comment_reaction \n" +
            "where comment_reaction.comment_comment_id = :commentId and comment_reaction.user_user_id = :userId",nativeQuery = true)
    List<CommentReaction> deleteCommentReactionByCommentIdAndUserID(Long commentId , Long userId);

    @Query(value = "select count(comment_comment_id) from comment_reaction where comment_comment_id = :commentId",nativeQuery = true)
   Long getCommentReactionCountByCommentId(Long commentId);

}
