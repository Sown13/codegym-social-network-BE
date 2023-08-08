package com.example.social_network.dto.user_friend_dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HaveBeenFriendsDTO {
    private boolean isAccepted;
    private String friendType;
}
