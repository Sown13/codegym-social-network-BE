package com.example.social_network.service.user_friend;

import com.example.social_network.model.friend.UserFriend;
import com.example.social_network.service.IGeneralService;

public interface IUserFriendService extends IGeneralService<UserFriend> {
    Iterable<UserFriend> findAllFriendsByUserId(Long id);
}
