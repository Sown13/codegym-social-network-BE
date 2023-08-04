package com.example.social_network.service.message.group_member;

import com.example.social_network.repo.message.IGroupMemberRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class GroupMember implements IGroupMemberService{
    @Autowired
    private IGroupMemberRepo groupMemberRepo;

    @Override
    public Iterable<GroupMember> findAll() {
        return null;
    }

    @Override
    public Optional<GroupMember> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public GroupMember save(GroupMember groupMember) throws Exception {
        return null;
    }

    @Override
    public void remove(Long id) {

    }
}
