package com.example.social_network.repo;

import com.example.social_network.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepo  extends JpaRepository<User,Long> {
    Optional<User>findUsersByAccountName(String accountName);
    Optional<User>findUsersByEmail(String email);

}
