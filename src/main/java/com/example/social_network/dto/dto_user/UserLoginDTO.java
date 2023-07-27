package com.example.social_network.dto.dto_user;

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
    private String role;
    private String fullName;
    private String avatar;
}