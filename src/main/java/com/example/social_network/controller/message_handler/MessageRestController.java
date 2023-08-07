package com.example.social_network.controller.message_handler;


import com.example.social_network.dto.message.MessageDTO;
import com.example.social_network.model.message.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

@Controller
@CrossOrigin("*")
public class MessageRestController {
    @Autowired
    SimpMessagingTemplate simpMessagingTemplate;

    @MessageMapping("/message")
    @SendTo("/chatroom/public")
    private MessageDTO receivePublicMessage(@Payload MessageDTO message){
        return message;
    }

    @MessageMapping("/private-message")
    private MessageDTO receivePrivateMessage(@Payload MessageDTO message){
        simpMessagingTemplate.convertAndSendToUser(message.getGroup().getGroupId().toString(), "/private",message);
//        => user/name/private/
        return message;
    }
}
