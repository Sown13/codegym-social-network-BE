package com.example.social_network.repo;

import com.example.social_network.model.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<User, Long> {
    Optional<User> findUserByAccountName(String accountName);

    Optional<User> findUsersByEmail(String email);

    Iterable<User> findUsersByAccountNameContaining(String account);

    //    @Query(value = "select u from User as u where u.accountName like %:name%")
//    Iterable<User> findAllByAccountName(String name);
    @Query(value = "select u from User as u order by u.createdDate")
    Iterable<User> SortUserByDayCreate();
    @Query(value = "select u from User as u order by u.accountName")
    Iterable<User> SortUserByAccountName();
}
