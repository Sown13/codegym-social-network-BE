package com.example.social_network.model.user;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;

@Entity
@Table(name = "users")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class User {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long userId;
    @Column(unique = true)
    @NotBlank(message = "Username is required")
    private String accountName;
    @NotBlank(message = "Password is required")
    @Size(min = 6, max = 32, message = "Password must be between 6 and 32 characters")
    private String password;
    @Column(unique = true)
    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email format")
    private String email;
    private String fullName;
    private String phone;
    private Date birthday;
    @Transient // tạm thời không lưu trữ trường này vào database
    private MultipartFile avatarFile; //  thêm trường MultipartFile cho avatar

    private String avatar="img/example-ava.jpg";
    private String hobby;
    private String role= "USER";
    private String address;
    private Date createdDate;
    private String background;
    private boolean isBlock=false;
    public User(String accountName, String password, String email, String fullName, String phone,
                Date birthday, MultipartFile avatarFile, String hobby, String address,String background) {
        this.accountName = accountName;
        this.password = password;
        this.email = email;
        this.fullName = fullName;
        this.phone = phone;
        this.birthday = birthday;
        this.avatarFile = avatarFile;
        this.hobby = hobby;
        this.address = address;
        this.createdDate = new Date();
        this.background=background;
    }

}
