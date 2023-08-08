package com.example.social_network.model.notification;

import com.example.social_network.model.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Table(name = "notification")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Notification {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long notificationId;
    private String type;
    private String content;
    private Date dateCreated;
    private Long groupId;
    @ManyToOne
    private User user;

    public Notification(String content, Date dateCreated, User user,Long groupId) {
        this.content = content;
        this.dateCreated = dateCreated;
        this.user = user;
        this.groupId = groupId;
    }
}
