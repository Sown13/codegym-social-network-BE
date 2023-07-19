package com.example.social_network.service.user;

import com.example.social_network.model.User;
import com.example.social_network.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class USerService implements IUserService {

    @Autowired
    private UserRepo userRepo;

    @Override
    public Iterable<User> findAll() {
        return userRepo.findAll();
    }

    @Override
    public Optional<User> findById(Long id) {
        return userRepo.findById(id);
    }

    @Override
    public User save(User user) throws Exception{
        if (userRepo.findUsersByAccountName(user.getAccountName()).isPresent()) {
            throw new Exception("Username already exists");
        }

        if (userRepo.findUsersByEmail(user.getEmail()).isPresent()) {
            throw new Exception("Email already exists");
        }

        return userRepo.save(user);
    }

    public User getUserByUsername(String username) throws Exception {
        Optional<User> optionalUser = userRepo.findUsersByAccountName(username);
        if (optionalUser.isPresent()) {
            return optionalUser.get();
        } else {
            throw new Exception("User not found");
        }
    }



    @Override
    public void remove(Long id) {

    }

//    public boolean isExist(String name, String email) {
//        if (userRepo.findUsersByAccountName(name).isEmpty() && userRepo.findUsersByEmail(email).isPresent()) {
//            return false;
//        }
//        return true;
//    }


}
