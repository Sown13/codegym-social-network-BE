package com.example.social_network.repo.user;

import com.example.social_network.model.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public interface IUserRepo extends JpaRepository<User, Long> {
    Optional<User> findUserByAccountName(String accountName);
    Optional<User> findUserByEmail(String email);

    Optional<User> findUsersByEmail(String email);

    List<User> findUsersByAccountNameContaining(String account);

//    @Query(value = "select u from User as u where u.accountName like %:name%")
//    Iterable<User> findAllByAccountName(String name);
//    @Query("SELECT CASE WHEN uf.sourceUser.userId = :id and uf.isAccepted = TRUE THEN uf.targetUser " +
//            "WHEN uf.targetUser.userId = :id and uf.isAccepted = TRUE THEN uf.sourceUser END " +
//            "FROM User us JOIN UserFriend uf ON us.userId = uf.targetUser.userId JOIN  uf ON us.userId = uf.sourceUser.userId")
//    Iterable<User> findListFriendByUserId(@Param("id") Long userId);
}
