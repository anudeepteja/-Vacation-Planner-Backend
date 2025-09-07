package com.vacation.vacationPlanner.dao;

import com.vacation.vacationPlanner.entity.UserGroup;
import com.vacation.vacationPlanner.entity.UserGroupIdCK;

import java.util.List;

public interface UserGroupDAO {
    void save(UserGroup userGroup);
    UserGroup findById(UserGroupIdCK id);
    List<UserGroup> findAll();
    List<UserGroup> findByUser(Integer userId);
    List<UserGroup> findByGroup(Integer groupId);
    void delete(UserGroupIdCK id);
}
