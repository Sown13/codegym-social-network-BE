package com.example.social_network.controller.loginsession;

import com.example.social_network.model.user.User;
import com.example.social_network.model.user.dto.UserLoginDTO;
import com.example.social_network.repo.UserRepo;
import com.example.social_network.service.user.IUserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("*")
public class LoginSession {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    IUserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @PostMapping("/login")
    public ResponseEntity<UserLoginDTO> login(@RequestBody User user, HttpServletRequest request) {
        Optional<User> storedUser = userService.findByAccountName(user.getAccountName());
        if (storedUser.isPresent()) {
            User foundUser = storedUser.get();
            if (foundUser.isBlock()) {
                UserLoginDTO loginResponse = new UserLoginDTO();
                loginResponse.setMessage("Account is blocked. Please contact support for assistance.");
                return new ResponseEntity<>(loginResponse, HttpStatus.UNAUTHORIZED);
            } else if (foundUser.getPassword().equals(user.getPassword())) {
                HttpSession session = request.getSession();
                session.setAttribute("userId", foundUser.getUserId());
                session.setAttribute("accountName", foundUser.getAccountName());
                session.setAttribute("role", foundUser.getRole());
                session.setMaxInactiveInterval(3600);

                UserLoginDTO loginResponse = new UserLoginDTO();
                loginResponse.setMessage("Login successfully.");
                loginResponse.setUserId(foundUser.getUserId());
                loginResponse.setAccountName(foundUser.getAccountName());
                loginResponse.setRole(foundUser.getRole());

                return ResponseEntity.ok(loginResponse);
            } else {
                UserLoginDTO loginResponse = new UserLoginDTO();
                loginResponse.setMessage("Invalid credentials. Login failed.");
                return new ResponseEntity<>(loginResponse, HttpStatus.UNAUTHORIZED);
            }
        } else {
            UserLoginDTO loginResponse = new UserLoginDTO();
            loginResponse.setMessage("Account not found. Login failed.");
            return new ResponseEntity<>(loginResponse, HttpStatus.NOT_FOUND);
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

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody User user) {
        User savedUser = null;
        ResponseEntity response = null;
        try {
            String hashPwd = passwordEncoder.encode(user.getPassword());
            user.setPassword(hashPwd);
            user.setCreatedDate(new Date(System.currentTimeMillis()));
            savedUser = userRepo.save(user);
            if (savedUser.getUserId() > 0) {
                response = ResponseEntity
                        .status(HttpStatus.CREATED)
                        .body("Given user details are successfully registered");
            }
        } catch (Exception ex) {
            response = ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("An exception occured due to " + ex.getMessage());
        }
        return response;
    }


    @RequestMapping("/user")
    public User getUserDetailsAfterLogin(Authentication authentication) {
        Optional<User> user = userRepo.findUserByAccountName(authentication.getName());
        if (user.isPresent()) {
            return user.get();
        } else {
            return null;
        }

    }

    @RequestMapping("/dung")
    public String dung() {
        return "dfadsf";
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

