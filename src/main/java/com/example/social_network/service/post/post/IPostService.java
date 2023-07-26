package com.example.social_network.service.post.post;

import com.example.social_network.model.post.Post;
import com.example.social_network.model.post.PostImage;
import com.example.social_network.service.IGeneralService;

public interface IPostService extends IGeneralService<Post> {
    Iterable<Post> findPostsByUserId(Long id);

    Iterable<PostImage> findImagesByPostId(Long id);

    Iterable<Post> findPostsOfAcceptedFriends(Long id);
}
