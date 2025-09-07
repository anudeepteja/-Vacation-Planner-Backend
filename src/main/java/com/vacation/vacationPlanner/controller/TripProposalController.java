package com.vacation.vacationPlanner.controller;

import com.vacation.vacationPlanner.entity.TripProposal;
import com.vacation.vacationPlanner.service.TripProposalService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/trip-proposals")
public class TripProposalController {

    private final TripProposalService tripProposalService;

    // Constructor injection
    public TripProposalController(TripProposalService tripProposalService) {
        this.tripProposalService = tripProposalService;
    }

    // ✅ Create Trip Proposal
    @PostMapping
    public TripProposal createTripProposal(@RequestBody TripProposal tripProposal) {
        tripProposalService.createTripProposal(tripProposal);
        return tripProposal;
    }

    // ✅ Get Trip Proposal by ID
    @GetMapping("/{id}")
    public TripProposal getTripProposalById(@PathVariable Integer id) {
        return tripProposalService.getTripProposalById(id);
    }

    // ✅ Get All Trip Proposals
    @GetMapping
    public List<TripProposal> getAllTripProposals() {
        return tripProposalService.getAllTripProposals();
    }

    // ✅ Get Trip Proposals by User ID
    @GetMapping("/user/{userId}")
    public List<TripProposal> getTripProposalsByUser(@PathVariable Integer userId) {
        return tripProposalService.getTripProposalsByUser(userId);
    }

    // ✅ Get Trip Proposals by Group ID
    @GetMapping("/group/{groupId}")
    public List<TripProposal> getTripProposalsByGroup(@PathVariable Integer groupId) {
        return tripProposalService.getTripProposalsByGroup(groupId);
    }

    // ✅ Update Trip Proposal
    @PutMapping("/{id}")
    public TripProposal updateTripProposal(@PathVariable Integer id, @RequestBody TripProposal updatedProposal) {
        TripProposal existing = tripProposalService.getTripProposalById(id);
        if (existing == null) {
            throw new RuntimeException("Trip Proposal not found with id " + id);
        }

        existing.setPlaceName(updatedProposal.getPlaceName());
        existing.setCostPerPerson(updatedProposal.getCostPerPerson());
        existing.setDescription(updatedProposal.getDescription());
        existing.setProposedBy(updatedProposal.getProposedBy());

        tripProposalService.updateTripProposal(existing);
        return existing;
    }

    // ✅ Delete Trip Proposal
    @DeleteMapping("/{id}")
    public String deleteTripProposal(@PathVariable Integer id) {
        tripProposalService.deleteTripProposal(id);
        return "Trip Proposal deleted with id " + id;
    }
}
