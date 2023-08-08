package com.example.social_network.controller.loginsession;

import com.example.social_network.model.user.User;
import com.example.social_network.dto.dto_user.UserLoginDTO;
import com.example.social_network.service.user.IUserService;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken.Payload;

import com.google.api.client.json.webtoken.JsonWebToken;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.crypto.SecretKey;
import java.security.interfaces.RSAPrivateKey;
import java.util.Base64;
import java.util.Collections;
import java.util.Optional;

@RestController
@CrossOrigin("*")
public class LoginSession {
    private static final String CLIENT_ID = "537600243548-sn10qab7em2fqo0ohhn02846g07tvf6v.apps.googleusercontent.com";

    @Autowired
    IUserService userService;

    @PostMapping("/longin_oauth2")
    public ResponseEntity<UserLoginDTO> login(@RequestBody String token , HttpServletRequest request){
        try {
            GoogleIdTokenVerifier verifier = new GoogleIdTokenVerifier.Builder(
                    new NetHttpTransport(), new GsonFactory()
            )
                    .setAudience(Collections.singletonList(CLIENT_ID))
                    .build();


            GoogleIdToken idToken = verifier.verify(token);
            if (idToken != null) {
                Payload payload = idToken.getPayload();
                String email = payload.getEmail();
                Optional<User> storedUser = userService.findByEmail(email);

                if (storedUser.isPresent()) {
                    User foundUser = storedUser.get();
                    if (foundUser.isBlock()) {
                        UserLoginDTO loginResponse = new UserLoginDTO();
                        loginResponse.setMessage("Account is blocked. Please contact support for assistance.");
                        return new ResponseEntity<>(loginResponse, HttpStatus.UNAUTHORIZED);
                    } else  {
                        HttpSession session = request.getSession();
                        session.setAttribute("userId", foundUser.getUserId());
                        session.setAttribute("accountName", foundUser.getAccountName());
                        session.setAttribute("role", foundUser.getRole());
                        session.setAttribute("fullName" ,foundUser.getFullName());
                        session.setAttribute("avatar" , foundUser.getAvatar());
                        session.setMaxInactiveInterval(3600);

                        UserLoginDTO loginResponse = new UserLoginDTO();
                        loginResponse.setMessage("Login successfully.");
                        loginResponse.setUserId(foundUser.getUserId());
                        loginResponse.setAccountName(foundUser.getAccountName());
                        loginResponse.setRole(foundUser.getRole());
                        loginResponse.setFullName(foundUser.getFullName());
                        loginResponse.setAvatar(foundUser.getAvatar());

                        return ResponseEntity.ok(loginResponse);
                    }
                } else {
                    UserLoginDTO loginResponse = new UserLoginDTO();
                    loginResponse.setMessage("Account not found. Login failed.");
                    return new ResponseEntity<>(loginResponse, HttpStatus.NOT_FOUND);
                }

            } else {
                UserLoginDTO loginResponse = new UserLoginDTO();
                loginResponse.setMessage("Invalid credentials. Login failed.");
                return new ResponseEntity<>(loginResponse, HttpStatus.UNAUTHORIZED);
            }
        } catch (Exception e) {
            UserLoginDTO loginResponse = new UserLoginDTO();
            loginResponse.setMessage("Account not found. Login failed.");
            return new ResponseEntity<>(loginResponse, HttpStatus.NOT_FOUND);
        }
    }

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
                session.setAttribute("fullName" ,foundUser.getFullName());
                session.setAttribute("avatar" , foundUser.getAvatar());
                session.setMaxInactiveInterval(3600);

                UserLoginDTO loginResponse = new UserLoginDTO();
                loginResponse.setMessage("Login successfully.");
                loginResponse.setUserId(foundUser.getUserId());
                loginResponse.setAccountName(foundUser.getAccountName());
                loginResponse.setRole(foundUser.getRole());
                loginResponse.setFullName(foundUser.getFullName());
                loginResponse.setAvatar(foundUser.getAvatar());

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

