package com.example.social_network.model.comment;

import com.example.social_network.model.user.User;
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
public class CommentReaction {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long cReactionId;
    private String reactionType;
    private Date dateCreated;
    @ManyToOne
    private User user;
    @ManyToOne
    private Comment comment;
}
