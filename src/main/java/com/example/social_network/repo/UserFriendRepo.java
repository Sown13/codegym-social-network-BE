package com.example.social_network.repo;

import com.example.social_network.model.friend.UserFriend;
import jakarta.persistence.PersistenceContext;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@PersistenceContext

@Repository
public interface UserFriendRepo extends JpaRepository<UserFriend, Long> {
    @Query("SELECT COUNT(*) FROM UserFriend u WHERE u.isAccepted = true AND u.targetUser = :userId")
    Long countAllByUserFriend(@Param("userId") Long userId);
    @Query("SELECT uf.targetUser FROM UserFriend uf WHERE uf.sourceUser.userId = :id and uf.isAccepted = true")
    Iterable<UserFriend> findUserFriendsByUserId(Long id);
}
