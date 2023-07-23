package com.example.social_network.controller;

import com.example.social_network.model.user.User;
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

    @GetMapping()
    private ResponseEntity<Iterable<User>> findAllUsers() {
        Iterable<User> listUser = userService.findAll();
        return new ResponseEntity<>(listUser, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    private ResponseEntity<Optional<User>> findUserByid(@PathVariable("id") Long id) {
        Optional<User> user = userService.findById(id);
        if (user.isPresent()) {
            return new ResponseEntity<>(user, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/name")
    private ResponseEntity<Iterable<User>> findUsersByAccountNameContaining(@RequestParam("name") String name) {
        Iterable<User> listUser = userService.findAllUsersByAccount(name);
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

    @GetMapping("sort-day-create")
    public ResponseEntity<Iterable<User>> sortUserByDayCreate() {
        return new ResponseEntity<>(userService.SortUserByDayCreate(), HttpStatus.OK);
    }

    @GetMapping("sort-account-name")
    public ResponseEntity<Iterable<User>> sortUserByAccountName() {
        return new ResponseEntity<>(userService.SortUserByAccountName(), HttpStatus.OK);
    }
}
