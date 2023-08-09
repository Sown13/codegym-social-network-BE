package com.example.social_network.service.message.group;

import com.example.social_network.model.message.Group;
import com.example.social_network.model.message.GroupMember;
import com.example.social_network.model.user.User;
import com.example.social_network.repo.message.IGroupRepo;
import com.example.social_network.service.message.group_member.IGroupMemberService;
import com.example.social_network.service.user.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class GroupService implements IGroupService{
    @Autowired
    private IGroupRepo groupRepo;
    @Autowired
    private IGroupMemberService groupMemberService;
    @Autowired
    private IUserService userService;

    @Override
    public Iterable<Group> findAll() {
        return groupRepo.findAll();
    }

    @Override
    public Optional<Group> findById(Long id) {
        return groupRepo.findById(id);
    }

    @Override
    public Group save(Group group) throws Exception {
        return groupRepo.save(group);
    }

    @Override
    public void remove(Long id) {
        groupRepo.deleteById(id);

    }

    @Override
    public List<Group> findGroupsByUserId(Long userId) {
        return groupRepo.findGroupsByUserId(userId);
    }

    @Override
    public Group createChatRoom(Long sourceUserId,Long tagetUserId) {
        String sourceUserFullName = userService.findById(sourceUserId).orElse(null).getFullName();
        String targetUserFullName = userService.findById(tagetUserId).orElse(null).getFullName();
        Group group = new Group();
        group.setDateCreated(new Date());
        group.setOwner(new User(sourceUserId));
        group.setGroupName("Nhóm chat của "+ sourceUserFullName + " và " + targetUserFullName);
        Group savedGroup = groupRepo.save(group);
        GroupMember groupMember1 = new GroupMember(new Date(),new User(sourceUserId),savedGroup);
        GroupMember groupMember2 = new GroupMember(new Date(),new User(tagetUserId),savedGroup);
        try {
            groupMemberService.save(groupMember1);
            groupMemberService.save(groupMember2);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return savedGroup;
    }
}
