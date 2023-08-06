package com.example.social_network.model.message;

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
public class GroupMember {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long groupMemberId;
    private Date dateJoined;
    @ManyToOne
    @JsonIgnore
    private User user;
    @ManyToOne
    @JsonIgnore
    private Group group;
}
