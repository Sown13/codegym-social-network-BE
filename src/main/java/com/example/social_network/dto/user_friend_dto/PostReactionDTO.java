package com.example.social_network.dto.user_friend_dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostReactionDTO {
    private Long reactionId;
    private Date dateCreated;
    private Long postPostId;
    private Long userUserId;
    private String accountName;

    private String reactionType;

    // Constructors, getters, setters, or any other methods (if needed)
}

