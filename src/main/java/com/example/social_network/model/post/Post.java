package com.example.social_network.model.post;

import com.example.social_network.model.comment.Comment;
import com.example.social_network.model.user.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Entity
@Table()
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Post {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long postId;
    private String textContent;
    private Date dateCreated;
    private Date dateUpdated;
    private String authorizedView;
    @ManyToOne
    @JsonIgnore
    private User user;
    @OneToMany(mappedBy = "post")
    List<PostImage> postImageList;
    @OneToMany(mappedBy = "post")
    List<Comment> commentList;

}
