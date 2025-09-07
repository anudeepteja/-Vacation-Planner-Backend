package com.vacation.vacationPlanner.service;


import com.vacation.vacationPlanner.dao.UserDAO;
import com.vacation.vacationPlanner.entity.User;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserDAO userDAO;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserDAO userDAO ,PasswordEncoder passwordEncoder) {
        this.userDAO = userDAO;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User saveUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userDAO.save(user);
    }

    @Override
    public Optional<User> getUserById(Long id) {
        return userDAO.findById(id);
    }

    @Override
    public List<User> getAllUsers() {
        return userDAO.findAll();
    }

    @Override
    public void deleteUser(Long id) {
        userDAO.deleteById(id);
    }

    @Override
    public User login(String usernameOrEmail, String password) {
        Optional<User> userOpt = userDAO.findByUsernameOrEmail(usernameOrEmail);

        if (userOpt.isPresent()) {
            User user = userOpt.get();
            if (passwordEncoder.matches(password, user.getPassword())) {  // TODO: hash check later
                return user;
            }
        }
        return null;
    }

    @Override
    public User getUserByUsername(String username) {
        Optional<User> userOpt = userDAO.findByUsername(username);
        if(userOpt.isPresent()){
            return userOpt.get();
        }
        return null;
    }

}