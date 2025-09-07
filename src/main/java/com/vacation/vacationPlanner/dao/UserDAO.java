package com.vacation.vacationPlanner.dao;

import com.vacation.vacationPlanner.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserDAO {
    User save(User user);  // Create or Update
    Optional<User> findById(Long id);  // Read
    List<User> findAll();  // Read all
    void deleteById(Long id);  // Delete

    Optional<User> findByUsernameOrEmail(String usernameOrEmail);

    Optional<User> findByUsername(String username);

}
