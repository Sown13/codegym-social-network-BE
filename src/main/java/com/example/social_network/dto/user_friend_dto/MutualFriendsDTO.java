package com.example.social_network.model.friend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MutualFriendsDTO {
    private Long sourceUserId;
    private Long targetUserId;
    private String accountName;
    private boolean isAccepted;
}
