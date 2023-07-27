package com.example.social_network.dto.post_image_dto;

import com.example.social_network.model.post.Post;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PostImageDTO {
    private Long postImageId;
    private String imgUrl;
    private Date dateCreated;
    private Post post;
}
