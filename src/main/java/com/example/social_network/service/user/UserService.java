package com.example.social_network.service.user;

import com.example.social_network.model.User;
import com.example.social_network.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class UserService implements IUserService{

    @Autowired
    UserRepo userRepo;
    @Override
    public Iterable<User> findAll() {
        return null;
    }

    @Override
    public Optional<User> findById(Long id) {
        return userRepo.findById(id);
    }

    @Override
    public User save(User user) {
        return null;
    }

    @Override
    public void remove(Long id) {

    }



    @Override
    public Optional<User> findByAccountName(String username) {
        return userRepo.findByAccountName(username);
    }
}
