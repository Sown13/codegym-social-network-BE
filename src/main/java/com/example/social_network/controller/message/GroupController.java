package com.example.social_network.controller.message;

import com.example.social_network.service.message.group.IGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin("*")
@RequestMapping("/groups")
public class GroupController {
    @Autowired
    private IGroupService groupService;
}
