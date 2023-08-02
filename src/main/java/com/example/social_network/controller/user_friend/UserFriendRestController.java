package com.example.social_network.controller.user_friend;

import com.example.social_network.dto.dto.CountMutualFriendDTO;
import com.example.social_network.dto.dto.HaveBeenFriendsDTO;
import com.example.social_network.dto.dto.SourceUserFriendDTO;
import com.example.social_network.dto.dto.TargetUserFriendDTO;
import com.example.social_network.model.user_friend.UserFriend;
import com.example.social_network.service.user_friend.UserFriendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.social_network.model.friend.dto.MutualFriendsDTO;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Optional;


@RestController
@CrossOrigin("*")
@RequestMapping("/user-friends")
public class UserFriendRestController {

    @Autowired
    UserFriendService userFriendService;

    @GetMapping("")
    public ResponseEntity<Iterable<UserFriend>> findAll() {
        Iterable<UserFriend> userFriendIterable = userFriendService.findAll();
        return new ResponseEntity<>(userFriendIterable, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<UserFriend>> findById(@PathVariable Long id) {
        Optional<UserFriend> userFriendOptional = userFriendService.findById(id);
        if (userFriendOptional.isPresent()) {
            return new ResponseEntity<>(userFriendOptional, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("")
    public ResponseEntity<UserFriend> sendFriendRequest(@RequestBody UserFriend userFriend) {
        if (userFriendService.findRelationShip(userFriend.getTargetUser().getUserId(), userFriend.getSourceUser().getUserId()) != null) {
            return new ResponseEntity<>(HttpStatus.ALREADY_REPORTED);
        } else {
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

    // delete khỏi danh sách , userFriend
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFriend(@PathVariable("id") Long id) {
        userFriendService.remove(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }


    // Lời mời s-u and false , source gửi lời mời đến target
    @GetMapping("/user/{id}/friend-request-sent")
    public ResponseEntity<Iterable<SourceUserFriendDTO>> sourceSendFriend(@PathVariable Long id) {
        Iterable<SourceUserFriendDTO> userFriendIterable = userFriendService.findUserFriendByUserFriendId(id);
        return new ResponseEntity<>(userFriendIterable, HttpStatus.OK);
    }

    // t-u and false , nhận  lời mời gửi đến từ s-u
    @GetMapping("/user/{id}/friend-request-receive")
    public ResponseEntity<Iterable<TargetUserFriendDTO>> targetSendFriend(@PathVariable Long id) {
        Iterable<TargetUserFriendDTO> userFriendIterable = userFriendService.findUserFriendByTargetUser(id);
        return new ResponseEntity<>(userFriendIterable, HttpStatus.OK);
    }


    @GetMapping("/relationship/{sourceId}/{targetId}")
    public ResponseEntity<Optional<UserFriend>> getRealtionShip(@PathVariable("targetId") Long targetId, @PathVariable("sourceId") Long sourceId) {
        Optional<UserFriend> relationShip = userFriendService.findRelationShip(targetId, sourceId);
        return new ResponseEntity<>(relationShip, HttpStatus.OK);
    }

    @GetMapping("/users/{userId}")
    public ResponseEntity<Iterable<UserFriend>> findAllFriendByUserId(@PathVariable Long userId) {
        Iterable<UserFriend> friendList = userFriendService.findAllFriendsByUserId(userId);
        return new ResponseEntity<>(friendList, HttpStatus.OK);
    }

//    @GetMapping("/mutual-friend/{targetUserId}")
//    public ResponseEntity<List<MutualFriendsDTO>> getAcceptedFriendsOfUser(@PathVariable Long targetUserId) {
//        List<com.example.social_network.model.friend.dto.MutualFriendsDTO> friends = userFriendService.findAcceptedUserFriendsByTargetUserId(targetUserId);
//        return new ResponseEntity<>(friends, HttpStatus.OK);
//    }


    @GetMapping("/count-accepted-friends/{targetUserId}")
    public ResponseEntity<CountMutualFriendDTO> countAcceptedFriends(@PathVariable Long targetUserId) {
        CountMutualFriendDTO result = userFriendService.countAcceptedFriendsByUserId(targetUserId);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/being-friend/{sourceId}/{targetId}")
    public ResponseEntity<Optional<UserFriend>> getListFriendsOfUserIdWhereHaveBeenFriend(@PathVariable("targetId") Long targetId, @PathVariable("sourceId") Long sourceId) {
        Optional<UserFriend> relationShip = userFriendService.findRelationShip(targetId, sourceId);
        System.out.println("Thong tin da la ban be chua");
        System.out.println(relationShip.get().isAccepted());
        if (relationShip.get().isAccepted() == false) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(relationShip, HttpStatus.OK);
    }


}



