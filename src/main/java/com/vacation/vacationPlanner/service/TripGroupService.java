package com.vacation.vacationPlanner.service;

import com.vacation.vacationPlanner.entity.TripGroup;

import java.util.List;

public interface TripGroupService {
    void saveGroup(TripGroup tripGroup);
    TripGroup getGroupById(Integer groupId);
    List<TripGroup> getAllGroups();
    void deleteGroup(Integer groupId);
}
