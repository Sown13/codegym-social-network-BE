package com.example.social_network.service.post.post;

import com.example.social_network.model.post.Post;
import com.example.social_network.repo.post.IPostRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PostService implements IPostService {
    @Autowired
    private IPostRepo IPostRepo;

    @Override
    public Iterable<Post> findAll() {
        return null;
    }

    @Override
    public Optional<Post> findById(Long id) {
        return IPostRepo.findById(id);
    }

    @Override
    public Post save(Post post) throws Exception {
        return IPostRepo.save(post);
    }

    @Override
    public void remove(Long id) {

    }
}
