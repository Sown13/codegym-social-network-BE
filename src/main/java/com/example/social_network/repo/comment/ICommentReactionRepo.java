package com.example.social_network.repo.comment;

import com.example.social_network.model.comment.CommentReaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICommentReactionRepo extends JpaRepository<CommentReaction,Long> {
}
