package com.example.social_network.repo;

import com.example.social_network.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepo  extends JpaRepository<User,Long> {

    Optional<User> findByAccountName(String username);

    @Query(value = "select u from User as u where u.accountName like %:name%")
    Iterable<User> findAllByAccountName(String name);
}
