package com.example.social_network.service.user;

import com.example.social_network.model.User;
import com.example.social_network.service.IGeneralService;

import java.util.Optional;

public interface IUserService extends IGeneralService<User> {

    Optional<User> findByAccountName(String username);

    Iterable<User> findAllByAccountName(String name);
}
