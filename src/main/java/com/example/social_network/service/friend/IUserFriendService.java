package com.example.social_network.service.friend;

import com.example.social_network.model.friend.UserFriend;
import com.example.social_network.service.IGeneralService;

public interface IUserFriendService extends IGeneralService<UserFriend> {
    Long countFriend(Long id);
}
