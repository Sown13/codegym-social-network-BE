package com.example.social_network.service.user_friends;

import com.example.social_network.model.User;
import com.example.social_network.model.friend.UserFriend;
import com.example.social_network.repo.UserFriendsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserFriendsService implements IUserFriendsService {
    @Autowired
    private UserFriendsRepo userFriendsRepo;

    @Override
    public Iterable<UserFriend> findAll() {
        return null;
    }

    @Override
    public Optional<UserFriend> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public UserFriend save(UserFriend userFriend) {
        return null;
    }

    @Override
    public void remove(Long id) {

    }

    @Override
    public Iterable<UserFriend> findAllFriendsByUserId(Long id) {
        return userFriendsRepo.findUserFriendsByUserId(id);
    }
}
