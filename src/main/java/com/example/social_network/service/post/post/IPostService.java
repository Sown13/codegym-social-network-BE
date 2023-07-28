package com.example.social_network.service.post.post;

import com.example.social_network.model.post.Post;
import com.example.social_network.service.IGeneralService;
import org.springframework.data.repository.query.Param;

public interface IPostService extends IGeneralService<Post> {
    Iterable<Post> findPostsByUserId(Long id);

    Iterable<Post> findPostsOfAcceptedFriends(Long id);

    Post updateAuthorizedViewByPostId( Long postId, String authorizedView);
}
