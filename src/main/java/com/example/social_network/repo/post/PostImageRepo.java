package com.example.social_network.repo.post;

import com.example.social_network.model.post.PostImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostImageRepo extends JpaRepository<PostImage,Long> {
}
