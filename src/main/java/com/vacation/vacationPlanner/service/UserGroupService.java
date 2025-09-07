package com.vacation.vacationPlanner.service;

import com.vacation.vacationPlanner.entity.UserGroup;
import com.vacation.vacationPlanner.entity.UserGroupIdCK;

import java.util.List;

public interface UserGroupService {
    void saveUserGroup(UserGroup userGroup);
    UserGroup getUserGroupById(UserGroupIdCK id);
    List<UserGroup> getAllUserGroups();
    List<UserGroup> getUserGroupsByUser(Integer userId);
    List<UserGroup> getUserGroupsByGroup(Integer groupId);
    void deleteUserGroup(UserGroupIdCK id);
}
