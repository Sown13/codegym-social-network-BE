package com.example.social_network.dto.user_dto;

import com.example.social_network.model.user.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserUpdateDTO {
    private Long userId;
    private String accountName;
    private String email;
    private String fullName;
    private String phone;
    private Date birthday;
    private String avatar;
    private String hobby;
    private String address;
    private String background;


    public UserUpdateDTO(User user) {
        this.userId = user.getUserId();
        this.accountName = user.getAccountName();
        this.email = user.getEmail();
        this.fullName = user.getFullName();
        this.phone = user.getPhone();
        this.birthday = user.getBirthday();
        this.avatar = user.getAvatar();
        this.hobby = user.getHobby();
        this.address = user.getAddress();
        this.background = user.getBackground();
    }
}
