package com.example.social_network.model.message;
import com.example.social_network.model.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Entity
@Table(name="user_group")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Group {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long groupId;
    private String groupName;
    private Date dateCreated;
    @OneToMany(mappedBy = "group")
    private List<GroupMember>groupMembers;
    @OneToMany(mappedBy = "group")
    private List<Message> messages;
    @ManyToOne
    private User owner;

}
