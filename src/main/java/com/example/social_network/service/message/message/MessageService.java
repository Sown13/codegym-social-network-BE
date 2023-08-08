package com.example.social_network.service.message.message;

import com.example.social_network.model.message.Message;
import com.example.social_network.repo.message.IMessageRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MessageService implements IMessageService {
    @Autowired
    private IMessageRepo messageRepo;
    @Override
    public Iterable<Message> findAll() {
        return null;
    }

    @Override
    public Optional<Message> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public Message save(Message message) throws Exception {
        return null;
    }

    @Override
    public void remove(Long id) {

    }
}
