package com.example.social_network.repo;

import com.example.social_network.model.friend.UserFriend;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserFriendRepo extends JpaRepository<UserFriend,Long>{
}
