package com.example.social_network.service.post.post;

import com.example.social_network.model.post.Post;
import com.example.social_network.model.post.PostImage;
import com.example.social_network.service.IGeneralService;

import java.util.List;

public interface IPostService extends IGeneralService<Post> {
    Iterable<Post> findPostsByUserId(Long id);

    Iterable<Post> findPostsOfAcceptedFriends(Long id);

    List<Post> findByEveryBody(Long postId, Long userId);
}
