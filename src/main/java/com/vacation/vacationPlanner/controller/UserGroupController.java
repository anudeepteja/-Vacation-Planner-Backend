package com.vacation.vacationPlanner.controller;

import com.vacation.vacationPlanner.entity.UserGroup;
import com.vacation.vacationPlanner.entity.UserGroupIdCK;
import com.vacation.vacationPlanner.service.UserGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@CrossOrigin
@RequestMapping("/api/user-groups")
public class UserGroupController {

    private final UserGroupService userGroupService;

    @Autowired
    public UserGroupController(UserGroupService userGroupService) {
        this.userGroupService = userGroupService;
    }

    // Create or Update
    @PostMapping
    public String saveUserGroup(@RequestBody UserGroup userGroup) {
        userGroupService.saveUserGroup(userGroup);
        return "UserGroup saved successfully!";
    }

    // Find by composite key
    @GetMapping("/{userId}/{groupId}")
    public UserGroup getUserGroupById(@PathVariable Integer userId, @PathVariable Integer groupId) {
        UserGroupIdCK id = new UserGroupIdCK(userId, groupId);
        return userGroupService.getUserGroupById(id);
    }

    // Find all
    @GetMapping
    public List<UserGroup> getAllUserGroups() {
        return userGroupService.getAllUserGroups();
    }

    // Find by User
    @GetMapping("/user/{userId}")
    public List<UserGroup> getUserGroupsByUser(@PathVariable Integer userId) {
        return userGroupService.getUserGroupsByUser(userId);
    }

    // Find by Group
    @GetMapping("/group/{groupId}")
    public List<UserGroup> getUserGroupsByGroup(@PathVariable Integer groupId) {
        return userGroupService.getUserGroupsByGroup(groupId);
    }

    // Delete
    @DeleteMapping("/{userId}/{groupId}")
    public String deleteUserGroup(@PathVariable Integer userId, @PathVariable Integer groupId) {
        UserGroupIdCK id = new UserGroupIdCK(userId, groupId);
        userGroupService.deleteUserGroup(id);
        return "UserGroup deleted successfully!";
    }
}

