package com.example.social_network.service.message.group_member;

import com.example.social_network.model.message.Group;
import com.example.social_network.model.message.GroupMember;
import com.example.social_network.model.user.User;
import com.example.social_network.repo.message.IGroupMemberRepo;
import com.example.social_network.repo.message.IGroupRepo;
import com.example.social_network.repo.user.IUserRepo;
import com.example.social_network.service.message.group.IGroupService;
import com.example.social_network.service.user.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class GroupMemberService implements IGroupMemberService {
    @Autowired
    private IGroupMemberRepo groupMemberRepo;
    @Autowired
    private IGroupService groupService;
    @Autowired
    private IUserService userService;

    @Override
    public GroupMember save(GroupMember groupMember) throws Exception {
        return groupMemberRepo.save(groupMember);
    }

    @Override
    public Iterable<GroupMember> findAll() {
        return groupMemberRepo.findAll();
    }

    @Override
    public Optional<GroupMember> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public void removeUserFromGroup(Long groupId, Long userId) {
        GroupMember groupMember = groupMemberRepo.findByGroupGroupIdAndUserUserId(groupId, userId);
        if (groupMember != null) {
            groupMemberRepo.delete(groupMember);
        }
    }

    @Override
    public void remove(Long id) {

    }


}
