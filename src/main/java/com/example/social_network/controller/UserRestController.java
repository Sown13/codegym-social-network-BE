package com.example.social_network.controller;

import com.example.social_network.model.User;
import com.example.social_network.repo.UserFriendRepo;
import com.example.social_network.service.friend.IUserFriendService;
import com.example.social_network.service.user.IUserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@RestController
@CrossOrigin("*")
@RequestMapping("/users")
public class UserRestController {
    @Autowired
    private IUserService userService;
//    @Autowired
//    private IUserFriendService userFriendService;
    @Autowired
    private UserFriendRepo userFriendRepo;

    @GetMapping()
    private ResponseEntity<Iterable<User>>findAllUsers(){
        Iterable<User>listUser=userService.findAll();
        return new ResponseEntity<>(listUser, HttpStatus.OK);
    }
    @GetMapping("/{id}")
    private ResponseEntity<Optional<User>>findUserByid(@PathVariable ("id")Long id){
        Optional<User>user=userService.findById(id);
        if(user.isPresent()){
            return new ResponseEntity<>(user,HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    @GetMapping("/name")
    private ResponseEntity<Iterable<User>>findUsersByAccountNameContaining(@RequestParam("name") String name){
        Iterable<User>listUser= userService.findAllUsersByAccount(name);
        return ResponseEntity.ok(listUser);
    }
    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@Valid @RequestBody User user, BindingResult result) {
        if (result.hasErrors()) {
            return ResponseEntity.badRequest().body(result.getAllErrors());
        }
        try {
            userService.save(user);
            return ResponseEntity.ok("User registered successfully");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/find-by-account-name")
    public ResponseEntity<Iterable<User>> findAllByUserAccountName(@RequestParam String accountName) {
        if (accountName == null) {
            return new ResponseEntity<>(userService.findAll(), HttpStatus.OK);
        } else {
            Iterable<User> users = userService.findAllByAccountName(accountName);
            return ResponseEntity.ok(users);
        }
    }

    @GetMapping("/detail/{id}")
    public ResponseEntity<Optional<User>> findById(@PathVariable Long id) {
        return new ResponseEntity<>(this.userService.findById(id), HttpStatus.OK);
    }
}
