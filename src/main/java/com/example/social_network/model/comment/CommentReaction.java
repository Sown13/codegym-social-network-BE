package com.example.social_network.model.comment;

import com.example.social_network.model.user.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.Date;

@Entity
@Table
@NoArgsConstructor
@AllArgsConstructor
@Data
public class CommentReaction {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long commentReactionId;
    private String reactionType = "LIKE";
    private Date dateCreated;
    @ManyToOne
    @JsonIgnore
    private User user;
    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Comment comment;
}
