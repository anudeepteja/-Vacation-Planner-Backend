package com.vacation.vacationPlanner.service;

import com.vacation.vacationPlanner.dao.UserGroupDAO;
import com.vacation.vacationPlanner.entity.UserGroup;
import com.vacation.vacationPlanner.entity.UserGroupIdCK;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserGroupServiceImpl implements UserGroupService {

    private final UserGroupDAO userGroupDAO;

    @Autowired
    public UserGroupServiceImpl(UserGroupDAO userGroupDAO) {
        this.userGroupDAO = userGroupDAO;
    }

    @Override
    public void saveUserGroup(UserGroup userGroup) {
        userGroupDAO.save(userGroup);
    }

    @Override
    public UserGroup getUserGroupById(UserGroupIdCK id) {
        return userGroupDAO.findById(id);
    }

    @Override
    public List<UserGroup> getAllUserGroups() {
        return userGroupDAO.findAll();
    }

    @Override
    public List<UserGroup> getUserGroupsByUser(Integer userId) {
        return userGroupDAO.findByUser(userId);
    }

    @Override
    public List<UserGroup> getUserGroupsByGroup(Integer groupId) {
        return userGroupDAO.findByGroup(groupId);
    }

    @Override
    public void deleteUserGroup(UserGroupIdCK id) {
        userGroupDAO.delete(id);
    }
}
