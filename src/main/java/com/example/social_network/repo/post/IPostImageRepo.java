package com.example.social_network.repo.post;

import com.example.social_network.model.post.PostImage;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Transactional
public interface IPostImageRepo extends JpaRepository<PostImage,Long> {
    @Query("SELECT pi FROM PostImage pi WHERE pi.post.postId = :postId")
    Iterable<PostImage> findImagesByPostId(Long postId);
    @Query("SELECT pi " +
            "FROM PostImage pi " +
            "INNER JOIN pi.post p " +
            "INNER JOIN p.user u " +
            "WHERE u.userId = :userId AND p.authorizedView = 'public'")
    Iterable<PostImage> findImagesByUserId(Long userId);
     @Query("SELECT pi " +
            "FROM PostImage pi " +
            "INNER JOIN pi.post p " +
            "INNER JOIN p.user u " +
            "WHERE u.userId = :userId ")
    Iterable<PostImage> findImagesByUserIdNotPublic(Long userId);

}
