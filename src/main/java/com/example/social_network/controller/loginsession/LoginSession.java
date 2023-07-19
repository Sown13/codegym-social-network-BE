package com.example.social_network.controller.loginsession;

import com.example.social_network.model.User;
import com.example.social_network.service.user.IUserService;
import com.example.social_network.service.user.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class LoginSession {

    @Autowired
    UserService userService;

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody User user, HttpServletRequest request) {
        Optional<User> storedUser = userService.findByAccountName(user.getAccountName());

        if (storedUser.isPresent() && storedUser.get().getPassword().equals(user.getPassword())) {
            HttpSession session = request.getSession();
            session.setAttribute("userId", storedUser.get().getUserId());
            session.setAttribute("accountName", storedUser.get().getAccountName());
            session.setAttribute("role", storedUser.get().getRole());
            session.setMaxInactiveInterval(3600);
            return ResponseEntity.ok("Login successfully" + storedUser.get().getUserId() + storedUser.get().getAccountName() + storedUser.get().getRole()  );
        } else {
            return ResponseEntity.ok("Login failed");
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

