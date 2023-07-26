package com.example.social_network.repo.post;

import com.example.social_network.model.post.Post;
import com.example.social_network.model.post.PostReaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IPostReactionRepo extends JpaRepository<PostReaction,Long> {

    Long countPostReactionsByPost(Post post);

    Long countPostReactionByPostAndReactionType(Post post, String reactionType);

    @Query(value = "select *  from post_reaction \n" +
            "join users on users.user_id = post_reaction.user_user_id\n" +
            "where post_reaction.post_post_id = :postId and post_reaction.user_user_id = :userId", nativeQuery = true)
    List<PostReaction> findByPostIdAndUserId(Long postId, Long userId);

    @Query(value = "select * from post_reaction where post_reaction.post_post_id  = :postId and post_reaction.user_user_id =:userId ", nativeQuery = true)
    List<PostReaction> DeleteUserIdAAndPostId( Long postId ,Long userId);
}
