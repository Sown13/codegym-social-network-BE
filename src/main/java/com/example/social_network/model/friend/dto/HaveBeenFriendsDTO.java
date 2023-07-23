package com.example.social_network.model.friend.dto;

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
