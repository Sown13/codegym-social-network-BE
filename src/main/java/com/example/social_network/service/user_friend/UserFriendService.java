package com.example.social_network.service.user_friend;

import com.example.social_network.dto.dto.SourceUserFriendDTO;
import com.example.social_network.dto.dto.TargetUserFriendDTO;
import com.example.social_network.model.user_friend.UserFriend;
import com.example.social_network.repo.user_friend.IUserFriendRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserFriendService implements IUserFriendService {

    @Autowired
    IUserFriendRepo IUserFriendRepo;

    @Override
    public Iterable<UserFriend> findAll() {
        return IUserFriendRepo.findAll();
    }

    @Override
    public Optional<UserFriend> findById(Long id) {
        return IUserFriendRepo.findById(id);
    }

    @Override
    public UserFriend save(UserFriend userFriend) {
        return IUserFriendRepo.save(userFriend);
    }

    @Override
    public void remove(Long id) {
        IUserFriendRepo.deleteById(id);
    }

    @Override
    public Iterable<UserFriend> findAllFriendsByUserId(Long id) {
        return IUserFriendRepo.findUserFriendsAcceptedByUserId(id);
    }

    @Override
    public Long countFriend(Long id) {
        return IUserFriendRepo.countAllByUserFriend(id);
    }

    @Override
    public List<SourceUserFriendDTO> findUserFriendByUserFriendId(Long id) {
        List<Object[]> results = IUserFriendRepo.findUserFriendByUserFriendId(id);
        List<SourceUserFriendDTO> userFriends = new ArrayList<>();

        for (Object[] result : results) {
            Long sourceUserId = (Long) result[0];
            Long targetUserId = (Long) result[1];
            String accountName = (String) result[2];
            boolean isAccepted = (Boolean) result[3];
            userFriends.add(new SourceUserFriendDTO(sourceUserId, targetUserId, accountName, isAccepted));
        }

        return userFriends;
    }

    @Override
    public List<TargetUserFriendDTO> findUserFriendByTargetUser(Long id) {
        List<Object[]> targetResults = IUserFriendRepo.findUserFriendByTargetUser(id);
        List<TargetUserFriendDTO> targetUserFriends = new ArrayList<>();

        for (Object[] targetResult : targetResults) {
            Long sourceUserId = (Long) targetResult[0];
            Long targetUserId = (Long) targetResult[1];
            String accountName = (String) targetResult[2];
            boolean isAccepted = (Boolean) targetResult[3];
            targetUserFriends.add(new TargetUserFriendDTO(sourceUserId, targetUserId, accountName, isAccepted));
        }

        return targetUserFriends;
    }

    @Override
    public Optional<UserFriend> findRelationShip(Long targetId, Long sourceId) {
        Optional<UserFriend> relationShip = IUserFriendRepo.findRelationShip(targetId, sourceId);
        return relationShip;
    }



}
