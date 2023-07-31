package com.example.social_network.dto.comment_dto;

import com.example.social_network.model.comment.CommentReaction;
import com.example.social_network.model.post.Post;
import com.example.social_network.model.user.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;
@NoArgsConstructor
@AllArgsConstructor
@Data
public class CommentDto {
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
