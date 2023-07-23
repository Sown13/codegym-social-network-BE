package com.example.social_network.controller;

import com.example.social_network.model.user.User;
import com.example.social_network.model.user.dto.UserDTO;
import com.example.social_network.model.user.dto.UserId;
import com.example.social_network.service.user.IUserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.io.File;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;


@RestController
@CrossOrigin("*")
@RequestMapping("/users")
public class UserRestController {
    @Autowired
    private IUserService userService;
    @GetMapping()
    private ResponseEntity<List<UserId>> findAllUsers() {
        List<UserId> listUser = userService.getAllUsersExceptPasswordAndBlock();
        return new ResponseEntity<>(listUser, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    private ResponseEntity<UserId> findAUserById(@PathVariable("id") Long id) {
        UserId user = userService.getUserByIdExceptPassword(id);
        if (user!=null) {
            return new ResponseEntity<>(user, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/search")
    private ResponseEntity<List<User>> findUsersByAccountNameContaining(@RequestParam("name") String name) {
        List<User> listUser = userService.findAllUsersByAccount(name);
        if (!listUser.isEmpty()) {
            return new ResponseEntity<>(listUser, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    //@PostMapping("/register")
//public ResponseEntity<?> registerUser(@Valid @RequestBody User user, BindingResult bindingResult,HttpServletRequest request) {
//    if (bindingResult.hasErrors()) {
//        List<String> errors = bindingResult.getFieldErrors()
//                .stream()
//                .map(DefaultMessageSourceResolvable::getDefaultMessage)
//                .collect(Collectors.toList());
//        return ResponseEntity.badRequest().body(errors);
//    }
//    try {
//        Date now = Date.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant());
//        user.setCreatedDate(now);
//        userService.save(user);
//        return ResponseEntity.ok("User registered successfully");
//    } catch (Exception e) {
//        return ResponseEntity.badRequest().body(e.getMessage());
//    }
//}
    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@Valid @ModelAttribute User user, BindingResult bindingResult, HttpServletRequest request) {
        if (bindingResult.hasErrors()) {
            List<String> errors = bindingResult.getFieldErrors()
                    .stream()
                    .map(DefaultMessageSourceResolvable::getDefaultMessage)
                    .collect(Collectors.toList());
            return ResponseEntity.badRequest().body(errors);
        }
        try {
            Date now = Date.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant());
            user.setCreatedDate(now);
            MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
            MultipartFile file = multipartRequest.getFile("avatarFile");
            if (file != null) {

                File dest = new File("/path/to/avatar/directory", Objects.requireNonNull(file.getOriginalFilename()));
                file.transferTo(dest);
                user.setAvatar(dest.getPath());
            }
            userService.save(user);
            return ResponseEntity.ok("User registered successfully");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }



    @PostMapping("/update/{id}")
    private ResponseEntity<?> updatePassword(@Valid @PathVariable("id") Long id, @RequestBody @Validated UserDTO userDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>(bindingResult.getAllErrors(), HttpStatus.BAD_REQUEST);
        }

        User user = userService.findById(id).orElse(null);
        if (user != null) {
            if (userService.checkPassword(user, userDTO.getPassword())) {
                user.setPassword(userDTO.getNewPassword());
                userService.update(user);
                return new ResponseEntity<>(user, HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @PostMapping("/update-is-block/{id}")
    private ResponseEntity<?>isBlockUser(@PathVariable("id")Long id){
        Optional<User> userOptional = userService.findById(id);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            user.setBlock(!user.isBlock());
            userService.update(user);
            return new ResponseEntity<>(user, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


    @PutMapping("/{id}")
    private ResponseEntity<?> updateUser(@Valid @PathVariable("id") Long id, @RequestBody User user, BindingResult result) {
        Optional<User> userOptional = userService.findById(id);
        if (userOptional.isPresent()) {
            user.setUserId(id);
            if (result.hasErrors()) {
                return ResponseEntity.badRequest().body(user);
            }
            try {
                userService.update(user);
                return new ResponseEntity<>(user, HttpStatus.OK);
            } catch (Exception e) {
                return ResponseEntity.badRequest().body(e.getMessage());
            }
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}

