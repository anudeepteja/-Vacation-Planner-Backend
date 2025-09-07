package com.vacation.vacationPlanner.controller;

import com.vacation.vacationPlanner.entity.User;
import com.vacation.vacationPlanner.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Map;
import org.springframework.http.HttpStatus;


import java.util.List;
import java.util.Optional;
@RestController
@CrossOrigin
@RequestMapping("/users")
public class UserContoller {

    private final UserService userService;

    // Constructor injection
    public UserContoller(UserService userService) {
        this.userService = userService;
    }

    //  Create User
    @PostMapping
    public User createUser(@RequestBody User user) {
        return userService.saveUser(user);
    }

    //  Get User by ID
    @GetMapping("/{id}")
    public Optional<User> getUserById(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    //  Get All Users
    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    //  Update User
    @PutMapping("/{id}")
    public User updateUser(@PathVariable Long id, @RequestBody User updatedUser) {
        return userService.getUserById(id).map(user -> {
            user.setFullName(updatedUser.getFullName());
            user.setUsername(updatedUser.getUsername());
            user.setEmail(updatedUser.getEmail());
            user.setPhoneNumber(updatedUser.getPhoneNumber());
            user.setPassword(updatedUser.getPassword());
            user.setWalletAmount(updatedUser.getWalletAmount());
            return userService.saveUser(user);
        }).orElseThrow(() -> new RuntimeException("User not found with id " + id));
    }

    //  Delete User
    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return "User deleted with id " + id;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> credentials) {
        String usernameOrEmail = credentials.get("username");
        String password = credentials.get("password");

        User user = userService.login(usernameOrEmail, password);

        if (user != null) {
            // ✅ success → return 200 OK + user
            return ResponseEntity.ok(user);
        } else {
            // ❌ failure → return 401 Unauthorized + error message
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body("Invalid username/email or password");
        }
    }

    // ✅ Get user by username
    @GetMapping("/username/{username}")
    public ResponseEntity<User> getUserByUsername(@PathVariable String username) {
        User user = userService.getUserByUsername(username);
        if (user != null) {
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}