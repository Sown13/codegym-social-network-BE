package com.example.social_network.model.post;

import com.example.social_network.model.user.User;
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
public class PostReaction {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long reactionId;
    private String reactionType;
    private Date dateCreated;
    @ManyToOne
    private User user;
    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Post post;
}
