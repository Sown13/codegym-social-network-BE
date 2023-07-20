package com.example.social_network.service.user_friend;

import com.example.social_network.model.friend.UserFriend;
import com.example.social_network.repo.UserFriendRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserFriendService implements IUserFriendService {

    @Autowired
    UserFriendRepo userFriendRepo;

    @Override
    public Iterable<UserFriend> findAll() {
        return userFriendRepo.findAll();
    }

    @Override
    public Optional<UserFriend> findById(Long id) {
        return userFriendRepo.findById(id);
    }

    @Override
    public UserFriend save(UserFriend userFriend) {
        return userFriendRepo.save(userFriend);
    }

    @Override
    public void remove(Long id) {
        userFriendRepo.deleteById(id);
    }
    @Override
    public Iterable<UserFriend> findAllFriendsByUserId(Long id) {
        return userFriendRepo.findUserFriendsByUserId(id);
    }

}
