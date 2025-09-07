package com.vacation.vacationPlanner.service;

import com.vacation.vacationPlanner.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    User saveUser(User user);
    Optional<User> getUserById(Long id);
    List<User> getAllUsers();
    void deleteUser(Long id);
    User login(String usernameOrEmail, String password);
    User getUserByUsername(String username);
}