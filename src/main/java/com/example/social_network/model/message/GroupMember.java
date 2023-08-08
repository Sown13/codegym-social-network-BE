package com.example.social_network.model.message;

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
public class GroupMember {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long groupMemberId;
    private Date dateJoined;
    @ManyToOne
    @JsonIgnore
    private User user;
    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Group group;

    public GroupMember(Date dateJoined, User user, Group group) {
        this.dateJoined = dateJoined;
        this.user = user;
        this.group = group;
    }
}
