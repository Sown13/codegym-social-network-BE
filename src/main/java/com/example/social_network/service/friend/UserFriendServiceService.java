package com.example.social_network.service.friend;

import com.example.social_network.model.friend.UserFriend;
import com.example.social_network.repo.UserFriendRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserFriendServiceService implements IUserFriendService {
    @Autowired
    private UserFriendRepo userFriendRepo;


    @Override
    public Iterable<UserFriend> findAll() {
        return userFriendRepo.findAll();
    }

    @Override
    public Optional<UserFriend> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public UserFriend save(UserFriend userFriend) throws Exception {
        return null;
    }

    @Override
    public void remove(Long id) {

    }


}
