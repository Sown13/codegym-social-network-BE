package com.example.social_network.controller.message;

import com.example.social_network.service.message.group_member.IGroupMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin("*")
@RequestMapping("/group-member")
public class GroupMemberController {
    @Autowired
    private IGroupMemberService groupMemberService;
}
