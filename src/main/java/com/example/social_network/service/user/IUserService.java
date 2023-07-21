package com.example.social_network.service.user;

import com.example.social_network.model.user.User;
import com.example.social_network.service.IGeneralService;

import java.util.Optional;

public interface IUserService extends IGeneralService<User> {
    Iterable<User> findAllUsersByAccount(String account);

    Optional<User> findByAccountName(String username);

    User update( User user);

//    Iterable<User> findAllByAccountName(String name);
}
