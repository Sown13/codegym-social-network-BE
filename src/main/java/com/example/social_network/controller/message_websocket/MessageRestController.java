package com.example.social_network.controller.message_websocket;


import com.example.social_network.dto.message.MessageDTO;
import com.example.social_network.dto.notification.NotificationDTO;
import com.example.social_network.model.notification.Notification;
import com.example.social_network.model.user.User;
import com.example.social_network.repo.user.IUserRepo;
import com.example.social_network.service.user.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@CrossOrigin("*")
public class MessageRestController {
    @Autowired
    SimpMessagingTemplate simpMessagingTemplate;
    @Autowired
    IUserService userService;

    @MessageMapping("/message")
    @SendTo("/chatroom/public")
    private MessageDTO receivePublicMessage(@Payload MessageDTO message){
        return message;
    }

    @MessageMapping("/private-message")
    private MessageDTO receivePrivateMessage(@Payload MessageDTO message){
        System.out.println(message.getGroupId());
        simpMessagingTemplate.convertAndSendToUser(message.getGroupId().toString(), "/private",message);
//        => /group/groupId/private
        NotificationDTO notification = new NotificationDTO("Bạn có 1 tin nhắn mới" + message.getTextContent(),message.getDateCreated(),message.getStatus());
        List<User> userList = userService.findUsersByGroupId(message
                .getGroupId())
                .stream()
                .filter(user-> !user.getUserId().equals(message.getUserId())).collect(Collectors.toList());
        for (User user: userList) {
            simpMessagingTemplate.convertAndSendToUser(user.getUserId().toString(),"/notification",notification);
//        =>/user/userId/notification
        }
        return message;
    }
}
