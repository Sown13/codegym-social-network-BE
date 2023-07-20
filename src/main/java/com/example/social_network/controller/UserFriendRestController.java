package com.example.social_network.controller;

import com.example.social_network.service.friend.IUserFriendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("/user-friend")
public class UserFriendRestController {
    @Autowired
    private IUserFriendService userFriendService;
    @GetMapping("/{id}")
    private ResponseEntity<Long>countFriend(@PathVariable("id")Long id){
        return new ResponseEntity<>(userFriendService.countFriend(id), HttpStatus.OK);
    }
}