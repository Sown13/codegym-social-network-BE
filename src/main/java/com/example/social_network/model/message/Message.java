package com.example.social_network.model.message;

import com.example.social_network.model.user.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Entity
@Table
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Message {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long messageId;
    private String textContent;
    private Date dateCreated;
    private boolean isRead;
    @ManyToOne
    private User user;
    @ManyToOne
    @JsonIgnore
    private Group group;
    @OneToMany(mappedBy = "message")
    private List<File> files;

}
