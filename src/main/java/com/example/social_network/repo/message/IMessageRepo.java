package com.example.social_network.repo.message;

import com.example.social_network.model.message.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IMessageRepo  extends JpaRepository<Message,Long> {
}
