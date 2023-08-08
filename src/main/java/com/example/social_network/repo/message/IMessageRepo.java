package com.example.social_network.repo.message;

import com.example.social_network.model.message.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IMessageRepo  extends JpaRepository<Message,Long> {

@Query("select me from Message  me WHERE me.user.userId=:userId")
    List<Message>getAllTheMessagesOfAPersonInTheGroup(Long userId);
@Query("select me from Message  me WHERE me.group.groupId=:groupId")
    List<Message> listMessByGroup(Long groupId);

}
