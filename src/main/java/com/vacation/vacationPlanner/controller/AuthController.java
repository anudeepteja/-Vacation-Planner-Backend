package com.vacation.vacationPlanner.controller;

import com.vacation.vacationPlanner.entity.User;
import com.vacation.vacationPlanner.security.JwtUtil;
import com.vacation.vacationPlanner.service.UserService;
import io.jsonwebtoken.Claims;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping("/auth")
public class AuthController {

    private final UserService userService;
    private final JwtUtil jwtUtil;

    public AuthController(UserService userService, JwtUtil jwtUtil) {
        this.userService = userService;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> credentials) {
        System.out.println("------------------" + credentials + "------------------");
        String usernameOrEmail = credentials.get("username");
        String password = credentials.get("password");

        User user = userService.login(usernameOrEmail, password);

        if (user != null) {
            String accessToken = jwtUtil.generateAccessToken(user.getUserId(), user.getUsername());
            String refreshToken = jwtUtil.generateRefreshToken(user.getUserId() ,user.getUsername());

            Map<String, Object> response = new HashMap<>();
            response.put("user", user);
            response.put("accessToken", accessToken);
            response.put("refreshToken", refreshToken);

            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body("Invalid username/email or password");
        }
    }

    // refresh endpoint
    @PostMapping("/refresh")
    public ResponseEntity<?> refresh(@RequestBody Map<String, String> request) {
        System.out.println("------------------ refresh : " + request + "------------------");
        String refreshToken = request.get("refreshToken");

        try {
            Claims claims = jwtUtil.getClaims(refreshToken);
            Long userId = Long.parseLong(claims.getSubject());
            Optional<User> userOpt = userService.getUserById(userId);

            if (userOpt.isEmpty()) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("User not found");
            }
            User user = userOpt.get();
            String newAccessToken = jwtUtil.generateAccessToken(user.getUserId(), user.getUsername());
            Map<String, Object> response = new HashMap<>();
            response.put("accessToken", newAccessToken);

            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body("Invalid or expired refresh token");
        }
    }
}
