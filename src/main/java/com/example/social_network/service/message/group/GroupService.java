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
}
