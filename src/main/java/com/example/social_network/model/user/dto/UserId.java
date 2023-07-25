package com.example.social_network.model.user.dto;

import com.example.social_network.model.user.User;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserId {
    private Long userId;
    private String accountName;
    private String email;
    private String fullName;
    private String phone;
    private Date birthday;
    private String avatar;
    private String hobby;
    private String role;
    private String address;
    private Date createdDate;
    private String background;
    private boolean isBlock;

    public UserId(User user) {
        this.userId = user.getUserId();
        this.accountName = user.getAccountName();
        this.email = user.getEmail();
        this.fullName = user.getFullName();
        this.phone = user.getPhone();
        this.birthday = user.getBirthday();
        this.avatar = user.getAvatar();
        this.hobby = user.getHobby();
        this.role = user.getRole();
        this.address = user.getAddress();
        this.createdDate = user.getCreatedDate();
        this.background = user.getBackground();
    }
}