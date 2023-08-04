package com.example.social_network.repo.message;

import com.example.social_network.model.message.Group;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IGroupRepo extends JpaRepository<Group,Long> {
}
