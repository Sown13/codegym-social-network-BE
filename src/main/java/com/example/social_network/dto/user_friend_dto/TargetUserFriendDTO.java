package com.example.social_network.dto.user_friend_dto;

import lombok.Data;

@Data
public class TargetUserFriendDTO {

    private Long sourceUserId;
    private Long targetUserId;
    private String accountName;
    private boolean isAccepted;

    public TargetUserFriendDTO(Long sourceUserId, Long targetUserId, String accountName, boolean isAccepted) {
        this.sourceUserId = sourceUserId;
        this.targetUserId = targetUserId;
        this.accountName = accountName;
        this.isAccepted = isAccepted;
    }
}
