package com.example.social_network.service.post.post;

import com.example.social_network.model.post.Post;
import com.example.social_network.model.post.PostImage;
import com.example.social_network.repo.post.PostRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PostService implements IPostService {
    @Autowired
    private PostRepo postRepo;

    @Override
    public Iterable<Post> findAll() {
        return postRepo.findAll();
    }

    @Override
    public Optional<Post> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public Post save(Post post) throws Exception {
        return null;
    }

    @Override
    public void remove(Long id) {

    }

    @Override
    public Iterable<Post> findPostsByUserId(Long id) {
        return postRepo.findPostsByUserId(id);
    }

    @Override
    public Iterable<PostImage> findImagesByPostId(Long id) {
        return postRepo.findImagesByPostId(id);
    }

    @Override
    public Iterable<Post> findPostsOfAcceptedFriends(Long id) {
        return postRepo.findPostsOfAcceptedFriends(id);
    }
}
