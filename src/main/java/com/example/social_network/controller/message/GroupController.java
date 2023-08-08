package com.example.social_network.controller.message;

import com.example.social_network.service.message.group.IGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("/groups")
public class GroupController {
    @Autowired
    private IGroupService groupService;

    @GetMapping("/user/{id}")
    public ResponseEntity<?> findPostById(@PathVariable Long id) {
        return new ResponseEntity<>(groupService.findGroupsByUserId(id), HttpStatus.OK);
    }
}
