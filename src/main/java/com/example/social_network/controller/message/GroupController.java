package com.example.social_network.controller.message;

import com.example.social_network.dto.message.GroupDto;
import com.example.social_network.model.message.Group;
import com.example.social_network.service.message.group.IGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.Optional;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("/groups")
public class GroupController {
    @Autowired
    private IGroupService groupService;
    @GetMapping()
    private ResponseEntity<?>findAllGroup(){
        return new ResponseEntity<>(groupService.findAll(),HttpStatus.OK);
    }
    @PostMapping()
    private ResponseEntity<?> createGroup(@RequestBody Group group) {
        Date date=new Date();
        try {
            group.setDateCreated(date);
            Group savedGroup = groupService.save(group);
            return new ResponseEntity<>(savedGroup, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to create group: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @DeleteMapping("/{groupId}")
    public ResponseEntity<?>deleteGroupById(@PathVariable Long groupId){
        Optional<Group>group=groupService.findById(groupId);
        if(group.isPresent()){
            groupService.remove(group.get().getGroupId());
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    @PutMapping("/{groupId}")
    public ResponseEntity<?> editTheNameOfTheGroup(@PathVariable Long groupId, @RequestBody GroupDto groupDTO) throws Exception {
        Optional<Group> groupOptional = groupService.findById(groupId);
        if (groupOptional.isPresent()) {
            Group existingGroup = groupOptional.get();
            existingGroup.setGroupName(groupDTO.getGroupName());
            Group updatedGroup = groupService.save(existingGroup);
            return new ResponseEntity<>(updatedGroup, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<?> findPostById(@PathVariable Long id) {
        return new ResponseEntity<>(groupService.findGroupsByUserId(id), HttpStatus.OK);
    }
}
