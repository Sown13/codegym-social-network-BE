package com.example.social_network.controller;

import com.example.social_network.model.User;
import com.example.social_network.service.user.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/users")
public class UserRestController {
    @Autowired
    private IUserService userService;

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

    @PutMapping("/{id}")
    public ResponseEntity<String> toggleUserBlockStatus(@PathVariable Long id) {
        Optional<User> optionalUser = userService.findById(id);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            user.setBlock(!user.isBlock());
            userService.save(user);
            return ResponseEntity.ok("User ID: " + id + " - isBlock status changed to: " + user.isBlock());
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
