package com.example.social_network.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
    private String accountName;
    private String password;
    private String email;
    private String fullName;
    private String phone;
    private Date birthday;
    private String avatar;
    private String hobby;
    private String role;
    private String address;


}
