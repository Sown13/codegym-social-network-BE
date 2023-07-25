package com.example.social_network.model.post;

import com.example.social_network.model.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Table()
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Post {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long PostId;
    private String textContent;
    private Date dateCreated;
    private Date updateCrated;
    private String authorizedView;
    @ManyToOne
    private User user;

}
