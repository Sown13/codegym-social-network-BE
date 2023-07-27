package com.example.social_network.model.post;

import com.example.social_network.model.user.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Table
@NoArgsConstructor
@AllArgsConstructor
@Data
public class PostReaction {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long reactionId;
    private String reactionType = "LIKE";
    private Date dateCreated;
    @ManyToOne
    private User user;
    @ManyToOne
    @JsonIgnore
    private Post post;

}
