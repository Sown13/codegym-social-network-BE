package com.example.social_network.dto.message;

import com.example.social_network.model.message.Group;
import com.example.social_network.model.user.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class GroupMemberDTO {
    private Long groupMemberId;
    private Date dateJoined;

    private Long userId;

    private Long groupId;
}
