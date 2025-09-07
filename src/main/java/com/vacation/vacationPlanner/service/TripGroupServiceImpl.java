package com.vacation.vacationPlanner.service;

import com.vacation.vacationPlanner.dao.TripGroupDAO;
import com.vacation.vacationPlanner.entity.TripGroup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TripGroupServiceImpl implements TripGroupService {

    private final TripGroupDAO tripGroupDAO;

    @Autowired
    public TripGroupServiceImpl(TripGroupDAO tripGroupDAO) {
        this.tripGroupDAO = tripGroupDAO;
    }

    @Override
    public void saveGroup(TripGroup tripGroup) {
        tripGroupDAO.save(tripGroup);
    }

    @Override
    public TripGroup getGroupById(Integer groupId) {
        return tripGroupDAO.findById(groupId);
    }

    @Override
    public List<TripGroup> getAllGroups() {
        return tripGroupDAO.findAll();
    }

    @Override
    public void deleteGroup(Integer groupId) {
        tripGroupDAO.delete(groupId);
    }
}
