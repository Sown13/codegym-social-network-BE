package com.example.social_network.repo.post;

import com.example.social_network.model.post.PostImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface IPostImageRepo extends JpaRepository<PostImage,Long> {
    @Query("SELECT pi FROM PostImage pi WHERE pi.post.postId = :postId")
    Iterable<PostImage> findImagesByPostId(Long postId);
}
