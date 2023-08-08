package com.example.social_network.service.user;

import com.example.social_network.dto.user_dto.UserId;
import com.example.social_network.model.user.User;
import com.example.social_network.model.user_friend.UserFriend;
import com.example.social_network.repo.user.IUserRepo;
import com.example.social_network.service.user_friend.IUserFriendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
 public class UserService implements IUserService {

    @Autowired
    private IUserRepo userRepo;
    @Autowired
    private IUserFriendService userFriendService;

    @Override
    public Iterable<User> findAll() {
        return userRepo.findAll();
    }

    @Override
    public Optional<User> findById(Long id) {
        return userRepo.findById(id);
    }


    @Override
    public User save(User user) throws Exception {
        if (userRepo.findUserByAccountName(user.getAccountName()).isPresent()) {
            throw new Exception("Username already exists");
        }

        if (userRepo.findUsersByEmail(user.getEmail()).isPresent()) {
            throw new Exception("Email already exists");
        }

        return userRepo.save(user);
    }

    public User getUserByUsername(String username) throws Exception {
        Optional<User> optionalUser = userRepo.findUserByAccountName(username);
        if (optionalUser.isPresent()) {
            return optionalUser.get();
        } else {
            throw new Exception("User not found");
        }
    }

    @Override
    public UserId getUserByIdExceptPassword(Long userId) {
        Optional<User> userOptional = userRepo.findById(userId);
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
        List<User> users = userRepo.findAll();
        List<UserId> userIdsList = new ArrayList<>();
        for (User user : users) {
            userIdsList.add(new UserId(user));
        }
        return userIdsList;
    }

    @Override
    public void remove(Long id) {

    }

    @Override
    public List<User> findAllUsersByAccount(String account) {
        return userRepo.findUsersByAccountNameContaining(account);
    }

    @Override
    public Optional<User> findByAccountName(String accountName) {
        return userRepo.findUserByAccountName(accountName);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return IUserRepo.findUserByEmail(email);
    }

    @Override
    public boolean checkPassword(User user, String password) {
        return user.getPassword().equals(password);
    }


    @Override
    public User update(User user) {
        return userRepo.save(user);
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

    @Override
    public List<User> findMutualFriend(Long sourceUserId, Long targetUserId) {
        List<UserFriend> sourceUserRelationList = new ArrayList<>();
        List<UserFriend> targetUserRelationList = new ArrayList<>();
        userFriendService.findAllFriendsByUserId(sourceUserId).forEach(sourceUserRelationList::add);
        userFriendService.findAllFriendsByUserId(targetUserId).forEach(targetUserRelationList::add);

        List<Long> listFriendIDSourceUser = new ArrayList<>();
        List<Long> listFriendIDTargetUser = new ArrayList<>();

        for (UserFriend relationshipSource : sourceUserRelationList){
            for(UserFriend relationshipTarget : targetUserRelationList){
                if (relationshipSource.getSourceUser().getUserId() == relationshipTarget.getSourceUser().getUserId()){

                }
            }
        }

        sourceUserRelationList.retainAll(targetUserRelationList);
        List<Long> listUserId = new ArrayList<>();
        for (int i = 0; i < sourceUserRelationList.size(); i++) {
            if (sourceUserRelationList.get(i).getSourceUser().getUserId() == sourceUserId
                    || sourceUserRelationList.get(i).getSourceUser().getUserId() == targetUserId){
                listUserId.add(sourceUserRelationList.get(i).getTargetUser().getUserId());
            }
            else if (sourceUserRelationList.get(i).getTargetUser().getUserId() == sourceUserId
            || sourceUserRelationList.get(i).getTargetUser().getUserId() == targetUserId) {
                listUserId.add(sourceUserRelationList.get(i).getSourceUser().getUserId());
            }
        }
        List<User> mutualFriend = new ArrayList<>();
        for (int i = 0; i < listUserId.size(); i++) {
            mutualFriend.add(userRepo.findById(listUserId.get(i)).get());
        }
        return mutualFriend;
    }

    @Override
    public List<User> findUsersByGroupId(Long id) {
        return userRepo.findUsersByGroupId(id);
    }
}
