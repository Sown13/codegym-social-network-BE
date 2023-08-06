package com.example.social_network.service.message.group;

import com.example.social_network.model.message.Group;
import com.example.social_network.repo.message.IGroupRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class GroupService implements IGroupService{
    @Autowired
    private IGroupRepo groupRepo;

    @Override
    public Iterable<Group> findAll() {
        return null;
    }

    @Override
    public Optional<Group> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public Group save(Group group) throws Exception {
        return null;
    }

    @Override
    public void remove(Long id) {

    }
}
