package com.example.social_network.service.message.message;

import com.example.social_network.model.message.Message;
import com.example.social_network.service.IGeneralService;

import java.util.List;

public interface IMessageService extends IGeneralService<Message> {
    List<Message>findAllByGroupGroupMembersUserUserId(Long userId);
    List<Message>getAllMessagesOfAGroupByGroupId(Long groupId);
}
