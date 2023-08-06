package com.example.social_network.controller.message_handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
@MessageMapping
public class MessageRestController {
    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    public void sendMessageToUser(Long userId, ChatMessage message) {
        messagingTemplate.convertAndSendToUser(userId.toString(), "/queue/chat", message);
    }
}
