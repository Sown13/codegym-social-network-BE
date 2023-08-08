package com.example.social_network.service.message.group;

import com.example.social_network.model.message.Group;
import com.example.social_network.service.IGeneralService;

import java.util.List;

public interface IGroupService extends IGeneralService<Group> {
    List<Group> findGroupsByUserId(Long userId);

    Group createChatRoom(Long sourceUserId, Long targetUserId);
}
