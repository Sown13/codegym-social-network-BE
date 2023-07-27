package com.example.social_network.model.comment;

import com.example.social_network.model.post.Post;
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
public class Comment {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long commentId;
    private String textContent;
    private Date dateCreated;
    private Date updateCreated;
    @ManyToOne
    private Post post;
    @ManyToOne
    private User user;
    @OneToMany(mappedBy = "comment")
    private List<CommentReaction> commentReactionList;
}
