package com.example.social_network.dto.post_dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class PostDTO {
    private String textContent;
    private String authorizedView;
    private Long userId;

}