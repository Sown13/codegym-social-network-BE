package com.example.social_network.service.post.post_image;

import com.example.social_network.model.post.PostImage;
import com.example.social_network.service.IGeneralService;

import java.util.List;

public interface IPostImageService extends IGeneralService<PostImage> {
    Iterable<PostImage> findImagesByPostId(Long id);

    List<PostImage> saveAll (List<PostImage> postImageList);

}
