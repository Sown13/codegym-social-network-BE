package com.example.social_network.repo.post;

import com.example.social_network.model.post.PostImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IPostImageRepo extends JpaRepository<PostImage,Long> {
}
