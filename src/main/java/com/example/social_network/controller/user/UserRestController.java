package com.example.social_network.controller.user;

import com.example.social_network.dto.user_dto.UserUpdateDTO;
import com.example.social_network.model.user.User;
import com.example.social_network.dto.user_dto.UserDTO;
import com.example.social_network.dto.user_dto.UserId;
import com.example.social_network.service.user.IUserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
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
        if (user != null) {
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

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@Valid @RequestBody User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            List<String> errors = bindingResult.getFieldErrors()
                    .stream()
                    .map(error -> error.getDefaultMessage())
                    .collect(Collectors.toList());
            return ResponseEntity.badRequest().body(errors);
        }
        try {
            Date now = Date.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant());
            user.setCreatedDate(now);
            user.setAvatar("https://firebasestorage.googleapis.com/v0/b/social-network-8069e.appspot.com/o/files%2Fdefault_avatar.png?alt=media&token=9a9960d1-b70d-4f60-acd8-4bc88277b686");
            user.setBackground("https://firebasestorage.googleapis.com/v0/b/social-network-8069e.appspot.com/o/files%2F0%2F1691518935801?alt=media&token=93a398e5-b210-4d4f-b96a-360aa095d5ae");
            userService.save(user);
            return ResponseEntity.ok("User registered successfully");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/update-pass/user/{id}")
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

    //Phương thức này test không có vấn đề
    @PostMapping("/update-is-block/{id}")
    private ResponseEntity<?> isBlockUser(@PathVariable("id") Long id) {
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
    private ResponseEntity<?> updateUserInformation(@Valid @PathVariable("id") Long id, @RequestBody UserUpdateDTO userUpdateDTO, BindingResult result) {
        Optional<User> userOptional = userService.findById(id);
        if (userOptional.isPresent()){
            User user=userOptional.get();
            user.setAccountName(userUpdateDTO.getAccountName());
            user.setAvatar(userUpdateDTO.getAvatar());
            user.setBirthday(userUpdateDTO.getBirthday());
            user.setAddress(userUpdateDTO.getAddress());
            user.setBackground(userUpdateDTO.getBackground());
            user.setEmail(userUpdateDTO.getEmail());
            user.setFullName(userUpdateDTO.getFullName());
            user.setHobby(userUpdateDTO.getHobby());
            user.setUserId(id);
            user.setPassword(user.getPassword());

            if(result.hasErrors()){
                return ResponseEntity.badRequest().body(result.getAllErrors());
            }
            try {
                userService.update(user);
                UserUpdateDTO userIdNew=new UserUpdateDTO(user);
                return new ResponseEntity<>(userIdNew, HttpStatus.OK);
            } catch (Exception e) {
                return ResponseEntity.badRequest().body(e.getMessage());
            }
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    @GetMapping("/{sourceUserId}/{targetUserId}/mutual-friends")
    private ResponseEntity<List<User>> findMutualFriend(@PathVariable Long sourceUserId, @PathVariable Long targetUserId){
        List<User> mutualFriendList = userService.findMutualFriend(sourceUserId,targetUserId);
        if(mutualFriendList.isEmpty()){
            return null;
        }
        return new ResponseEntity<>(mutualFriendList,HttpStatus.OK);
    }
}

