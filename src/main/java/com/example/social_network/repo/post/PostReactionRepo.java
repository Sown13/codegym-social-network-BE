package com.example.social_network.repo.post;

import com.example.social_network.model.post.PostReaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostReactionRepo extends JpaRepository<PostReaction, Long> {

    @Query(value = "select reaction_id , date_created , post_post_id , user_user_id , users.account_name , post_reaction.reaction_type  from post_reaction \n" +
            "join users on users.user_id = post_reaction.user_user_id\n" +
            "where post_reaction.post_post_id = :postId and post_reaction.user_user_id = :userId", nativeQuery = true)
    List<Object[]> findByPostIdAndUserId(Long postId, Long userId);

    @Query(value = "select * from post_reaction where post_reaction.user_user_id = :userId and post_reaction.post_post_id = :postId", nativeQuery = true)
    List<PostReaction> findByUserIdAAndPostId(Long userId, Long postId);
}
