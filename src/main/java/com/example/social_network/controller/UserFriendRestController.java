package com.example.social_network.controller;

import com.example.social_network.model.friend.UserFriend;
import com.example.social_network.service.friend.UserFriendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.Optional;



@RestController
@RequestMapping("/user-friends")
public class UserFriendRestController {

    @Autowired
    UserFriendService userFriendService;

    @GetMapping("/")
    public ResponseEntity<Iterable<UserFriend>> findAll() {
        Iterable<UserFriend> userFriendIterable = userFriendService.findAll();
        return new ResponseEntity<>(userFriendIterable, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<UserFriend>> findById(@PathVariable Long id){
        Optional<UserFriend> userFriendOptional = userFriendService.findById(id);
        if (userFriendOptional.isPresent()){
            return new ResponseEntity<>(userFriendOptional , HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }

    @PostMapping("/")
    public ResponseEntity<UserFriend> sendFriendRequest(@RequestBody UserFriend userFriend) {
        LocalDate date = LocalDate.now();
        Date utilDate = Date.from(date.atStartOfDay(ZoneId.systemDefault()).toInstant());
        userFriend.setDateRequested(utilDate);
        userFriend.setAccepted(false);
        UserFriend savedUserFriend = userFriendService.save(userFriend);
        if (savedUserFriend != null) {
            return new ResponseEntity<>(savedUserFriend, HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Optional<UserFriend>> acceptFriendRequest(@PathVariable("id") Long id) {
        Optional<UserFriend> userFriendOptional = userFriendService.findById(id);
        if (userFriendOptional.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        LocalDate date = LocalDate.now();
        Date utilDate = Date.from(date.atStartOfDay(ZoneId.systemDefault()).toInstant());
        UserFriend userFriend = userFriendOptional.get();
        userFriend.setAccepted(true);
        userFriend.setDateAccepted(utilDate);
        userFriendService.save(userFriend);
        return ResponseEntity.ok(userFriendOptional);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFriend(@PathVariable("id") Long id) {
        userFriendService.remove(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}



