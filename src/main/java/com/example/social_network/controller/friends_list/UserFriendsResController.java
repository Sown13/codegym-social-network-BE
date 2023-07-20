package com.example.social_network.controller.friends_list;

import com.example.social_network.model.friend.UserFriend;
import com.example.social_network.service.user_friends.IUserFriendsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("user-friends")
public class UserFriendsResController {
    @Autowired
    private IUserFriendsService userFriendsService;

    @GetMapping("{id}")
    public ResponseEntity<Iterable<UserFriend>> findAllByUserId(@PathVariable Long id) {
        return new ResponseEntity<>(userFriendsService.findAllFriendsByUserId(id), HttpStatus.OK);
    }

}