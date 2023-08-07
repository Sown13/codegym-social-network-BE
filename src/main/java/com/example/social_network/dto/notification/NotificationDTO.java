package com.example.social_network.dto.notification;

import com.example.social_network.dto.Status;
import com.example.social_network.model.user.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class NotificationDTO {
    private String type;
    private String content;
    private String dateCreated;
    private User userId;
    private Status status;

    public NotificationDTO(String content, String dateCreated, Status status) {
        this.content = content;
        this.dateCreated = dateCreated;
        this.status = status;
    }
}