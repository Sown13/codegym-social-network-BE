package com.example.social_network.repo.post;

import com.example.social_network.model.post.Post;
import com.example.social_network.model.post.PostReaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostReactionRepo extends JpaRepository<PostReaction,Long> {

    Long countPostReactionsByPost(Post post);

    Long countPostReactionByPostAndReactionType(Post post, String reactionType);
}
