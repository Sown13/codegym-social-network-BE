package com.example.social_network.dto.message;

import com.example.social_network.dto.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MessageDTO {
    private String textContent;
    private Long userId;
    private String fullName;
    private Long groupId;
    private String groupName;
    private Status status;
    private String dateCreated;
}
