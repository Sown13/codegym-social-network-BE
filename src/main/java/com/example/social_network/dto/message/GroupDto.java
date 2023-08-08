package com.example.social_network.dto.message;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class GroupDto {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long groupId;
    private String groupName;
}
