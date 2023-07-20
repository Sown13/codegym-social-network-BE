package com.example.social_network.service.user_friends;

import com.example.social_network.model.User;
import com.example.social_network.model.friend.UserFriend;
import com.example.social_network.service.IGeneralService;

public interface IUserFriendsService extends IGeneralService<UserFriend> {
    Iterable<UserFriend> findAllFriendsByUserId(Long id);
}
