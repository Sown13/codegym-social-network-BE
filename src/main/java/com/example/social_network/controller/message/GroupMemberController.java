package com.example.social_network.controller.message;
import com.example.social_network.dto.message.GroupMemberDTO;
import com.example.social_network.model.message.Group;
import com.example.social_network.model.message.GroupMember;
import com.example.social_network.model.user.User;
import com.example.social_network.service.message.group.IGroupService;
import com.example.social_network.service.message.group_member.IGroupMemberService;
import com.example.social_network.service.user.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("/group-members")
public class GroupMemberController {
    @Autowired
    private IGroupMemberService groupMemberService;
    @Autowired
    private IGroupService groupService;
    @Autowired
    private IUserService userService;

    @PostMapping("")
    public ResponseEntity<?> createGroupMember(@RequestBody GroupMemberDTO groupMemberRequest) throws Exception {
        User user = userService.findById(groupMemberRequest.getUserId()).orElse(null);
        Group group = groupService.findById(groupMemberRequest.getGroupId()).orElse(null);
        if (user == null || group == null) {
            return new ResponseEntity<>("User hoặc Group không tồn tại", HttpStatus.BAD_REQUEST);
        }
        GroupMember groupMember = new GroupMember();
        groupMember.setDateJoined(groupMemberRequest.getDateJoined());
        groupMember.setUser(user);
        groupMember.setGroup(group);

        return new ResponseEntity<>(groupMemberService.save(groupMember), HttpStatus.OK);
    }
    @DeleteMapping("/{groupId}/members/{userId}")
    public ResponseEntity<String> removeUserFromGroup(
            @PathVariable Long groupId,
            @PathVariable Long userId) {
        groupMemberService.removeUserFromGroup(groupId, userId);
        return ResponseEntity.ok("User removed from group successfully.");
    }
}

