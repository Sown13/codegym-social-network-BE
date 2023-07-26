package com.example.social_network.service.post.post_imge;

import com.example.social_network.model.post.PostImage;
import com.example.social_network.repo.post.PostImageRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PostImageService implements IPostImageService {
    @Autowired
    private PostImageRepo postImageRepo;

    @Override
    public Iterable<PostImage> findAll() {
        return postImageRepo.findAll();
    }

    @Override
    public Optional<PostImage> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public PostImage save(PostImage postImage) throws Exception {
        return postImageRepo.save(postImage);
    }

    @Override
    public void remove(Long id) {
        postImageRepo.deleteById(id);
    }
}
