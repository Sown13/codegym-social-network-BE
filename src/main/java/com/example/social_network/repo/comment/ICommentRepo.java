package com.example.social_network.repo.comment;

import com.example.social_network.model.comment.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ICommentRepo extends JpaRepository<Comment,Long> {
    @Query("FROM Comment c WHERE c.post.postId = :postId")
    List<Comment> getAllCommentsByPostId(@Param("postId") Long id);
}
