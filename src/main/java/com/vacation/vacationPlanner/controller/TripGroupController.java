package com.vacation.vacationPlanner.controller;

import com.vacation.vacationPlanner.entity.TripGroup;
import com.vacation.vacationPlanner.service.TripGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/groups")
public class TripGroupController {

    private final TripGroupService tripGroupService;

    @Autowired
    public TripGroupController(TripGroupService tripGroupService) {
        this.tripGroupService = tripGroupService;
    }

    // Create / Save Group
    @PostMapping
    public TripGroup createGroup(@RequestBody TripGroup tripGroup) {
        tripGroupService.saveGroup(tripGroup);
        return tripGroup;
    }

    // Get Group by ID
    @GetMapping("/{id}")
    public TripGroup getGroupById(@PathVariable("id") Integer groupId) {
        return tripGroupService.getGroupById(groupId);
    }

    // Get All Groups
    @GetMapping
    public List<TripGroup> getAllGroups() {
        return tripGroupService.getAllGroups();
    }

    // Delete Group
    @DeleteMapping("/{id}")
    public String deleteGroup(@PathVariable("id") Integer groupId) {
        tripGroupService.deleteGroup(groupId);
        return "Group with ID " + groupId + " has been deleted.";
    }
}
