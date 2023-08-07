package com.example.social_network.service.message.message;

import com.example.social_network.model.message.Message;
import com.example.social_network.repo.message.IMessageRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MessageService implements IMessageService {
    @Autowired
    private IMessageRepo messageRepo;
    @Override
    public Iterable<Message> findAll() {
        return messageRepo.findAll();
    }

    @Override
    public Optional<Message> findById(Long id) {
        return messageRepo.findById(id);
    }

    @Override
    public Message save(Message message) throws Exception {
        return messageRepo.save(message);
    }

    @Override
    public void remove(Long id) {
        messageRepo.deleteById(id);

    }

    @Override
    public List<Message> findAllByGroupGroupMembersUserUserId(Long userId) {
        return messageRepo.getAllTheMessagesOfAPersonInTheGroup(userId);
    }

    @Override
    public List<Message> getAllMessagesOfAGroupByGroupId(Long groupId) {
        return messageRepo.listMessByGroup(groupId);
    }
}
