package com.example.social_network.controller.loginsession;

import com.example.social_network.model.user.User;
import com.example.social_network.service.user.IUserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@CrossOrigin("*")
public class LoginSession {

    @Autowired
    IUserService userService;


    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody User user, HttpServletRequest request) {
        Optional<User> storedUser = userService.findByAccountName(user.getAccountName());
        if (storedUser.isPresent()) {
            User foundUser = storedUser.get();
            if (foundUser.isBlock()) {
                return ResponseEntity.ok("Account is blocked. Please contact support for assistance.");
            } else if (foundUser.getPassword().equals(user.getPassword())) {
                HttpSession session = request.getSession();
                session.setAttribute("userId", foundUser.getUserId());
                session.setAttribute("accountName", foundUser.getAccountName());
                session.setAttribute("role", foundUser.getRole());
                session.setMaxInactiveInterval(3600);
                return ResponseEntity.ok("Login successfully. User ID: " + foundUser.getUserId() + ", Account Name: " + foundUser.getAccountName() + ", Role: " + foundUser.getRole());
            } else {
                return ResponseEntity.ok("Invalid credentials. Login failed.");
            }
        } else {
            return ResponseEntity.ok("Account not found. Login failed.");
        }
    }


    @GetMapping("/logout")
        public ResponseEntity<String> logout(HttpServletRequest request) {
            HttpSession session = request.getSession(false);
            if (session != null) {
                session.invalidate(); // Xóa session hiện tại
            }
            return ResponseEntity.ok("Logged out successfully");
        }

//    @GetMapping("/home")
//    public String home(HttpServletRequest request) {
//        HttpSession session = request.getSession(false);
//        if (session != null && session.getAttribute("userId") != null) {
//            // Tài khoản đăng nhập còn tồn tại trong session
//            return "home";
//        } else {
//            // Tài khoản đăng nhập không tồn tại trong session
//            return "redirect:/login";
//        }
//    }
}

