package com.example.social_network.repo;

import com.example.social_network.model.friend.UserFriend;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserFriendsRepo extends JpaRepository<UserFriend, Long> {
    @Query("SELECT uf.targetUser FROM UserFriend uf WHERE uf.sourceUser.userId = :id and uf.isAccepted = true")
    Iterable<UserFriend> findUserFriendsByUserId(Long id);
}
