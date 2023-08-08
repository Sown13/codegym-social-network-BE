package com.example.social_network.controller.message;

import com.example.social_network.model.message.Message;
import com.example.social_network.service.message.message.IMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/messages")
public class MessageController {
    @Autowired
    private IMessageService messageService;
    @GetMapping()
    private ResponseEntity<?>findAllMessage(){
        return new ResponseEntity<>(messageService.findAll(), HttpStatus.OK);
    }
    @PostMapping()
    private ResponseEntity<?> createMessage(@RequestBody Message message) {
        Date date=new Date();

        try {
            message.setDateCreated(date);
            Message messageNew = messageService.save(message);
            return new ResponseEntity<>(messageNew, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Lỗi xử lý", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @DeleteMapping("/{messId}")
    private ResponseEntity<Void>deleteMessageById(@PathVariable Long messId) {
        Optional<Message>message=messageService.findById(messId);
        if(message.isPresent()){
            messageService.remove(message.get().getMessageId());
            return new ResponseEntity<>( HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    @GetMapping("/list-mess-user/{userId}")
    private ResponseEntity<?>findAllByGroupGroupMembersUserUserId(@PathVariable Long userId ){
        List<Message>listMessageByUser=messageService.findAllByGroupGroupMembersUserUserId(userId);
        return new ResponseEntity<>(listMessageByUser,HttpStatus.OK);
    }
    @GetMapping("/list-mess-group/{groupId}")
    private ResponseEntity<?>getAllMessagesOfAGroupByGroupId(@PathVariable Long groupId){
        List<Message>listMessagaByGroupId=messageService.getAllMessagesOfAGroupByGroupId(groupId);
        return new ResponseEntity<>(listMessagaByGroupId,HttpStatus.OK);
    }
}
