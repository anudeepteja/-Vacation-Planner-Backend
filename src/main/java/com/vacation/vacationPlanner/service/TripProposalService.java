package com.vacation.vacationPlanner.service;

import com.vacation.vacationPlanner.entity.TripProposal;

import java.util.List;

public interface TripProposalService {
    void createTripProposal(TripProposal tripProposal);
    TripProposal getTripProposalById(Integer id);
    List<TripProposal> getAllTripProposals();
    List<TripProposal> getTripProposalsByUser(Integer userId);

    List<TripProposal> getTripProposalsByGroup(Integer groupId);
    void updateTripProposal(TripProposal tripProposal);
    void deleteTripProposal(Integer id);
}
