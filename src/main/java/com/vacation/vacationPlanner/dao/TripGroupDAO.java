package com.vacation.vacationPlanner.dao;

import com.vacation.vacationPlanner.entity.TripGroup;

import java.util.List;

public interface TripGroupDAO {
    void save(TripGroup tripGroup);
    TripGroup findById(Integer groupId);
    List<TripGroup> findAll();
    void delete(Integer groupId);
}
