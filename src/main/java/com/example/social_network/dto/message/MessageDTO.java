package com.example.social_network.dto.message;

import com.example.social_network.model.message.File;
import com.example.social_network.model.message.Group;
import com.example.social_network.model.user.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MessageDTO {

    private Long messageId;
    private String textContent;
    private Date dateCreated;
    private boolean isRead;
    private User user;
    private Group group;
    private List<File> files;
    private Status status;
}
