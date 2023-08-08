package com.example.social_network.service.user;

import com.example.social_network.model.user.User;
import com.example.social_network.dto.dto_user.UserId;
import com.example.social_network.repo.user.IUserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
 public class UserService implements IUserService {

    @Autowired
    private IUserRepo IUserRepo;
    @Override
    public Iterable<User> findAll() {
        return IUserRepo.findAll();
    }
    @Override
    public Optional<User> findById(Long id) {
      return IUserRepo.findById(id);
    }


    @Override
    public User save(User user) throws Exception {
        if (IUserRepo.findUserByAccountName(user.getAccountName()).isPresent()) {
            throw new Exception("Username already exists");
        }

        if (IUserRepo.findUsersByEmail(user.getEmail()).isPresent()) {
            throw new Exception("Email already exists");
        }

        return IUserRepo.save(user);
    }

    public User getUserByUsername(String username) throws Exception {
        Optional<User> optionalUser = IUserRepo.findUserByAccountName(username);
        if (optionalUser.isPresent()) {
            return optionalUser.get();
        } else {
            throw new Exception("User not found");
        }
    }
    @Override
    public UserId getUserByIdExceptPassword(Long userId) {
        Optional<User> userOptional = IUserRepo.findById(userId);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            // ^ tạo đối tượng UserId với trường userId được lấy từ đối tượng User


            // đặt các trường password và isBlock thành null
            return new UserId(user);
        }
        return null;
    }

    @Override
    public List<UserId> getAllUsersExceptPasswordAndBlock() {
        List<User>users= IUserRepo.findAll();
        List<UserId>userIdsList=new ArrayList<>();
        for (User user :users) {
            userIdsList.add(new UserId(user));
        }
        return userIdsList;
    }

    @Override
    public void remove(Long id) {

    }

    @Override
    public List<User> findAllUsersByAccount(String account) {
        return IUserRepo.findUsersByAccountNameContaining(account);
    }

    @Override
    public Optional<User> findByAccountName(String accountName) {
        return IUserRepo.findUserByAccountName(accountName);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return IUserRepo.findUserByEmail(email);
    }

    @Override
    public boolean checkPassword(User user, String password) {
      return  user.getPassword().equals(password);
    }


    @Override
    public User update(User user) {
        return IUserRepo.save(user);
    }



//    @Override
//    public Iterable<User> findAllByAccountName(String name) {
//        return userRepo.findUserByAccountName(name);
//    }


//    @Override
//    public List<User> findAllFriendsByUserId(Long userId) {
//        Iterable<User> friendList = userRepo.findListFriendByUserId(userId);
//
//        return null;
//    }
}
