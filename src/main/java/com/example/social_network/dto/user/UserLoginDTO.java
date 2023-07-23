package com.example.social_network.dto.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserLoginDTO {
    private String message;
    private Long userId;
    private String accountName;
    private String fullName;
    private String role;

}