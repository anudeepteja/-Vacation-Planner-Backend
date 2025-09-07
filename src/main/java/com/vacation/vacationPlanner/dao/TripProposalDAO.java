package com.vacation.vacationPlanner.dao;

import java.util.List;
import com.vacation.vacationPlanner.entity.TripProposal;
public interface TripProposalDAO {
    void save(TripProposal tripProposal);
    TripProposal findById(Integer id);
    List<TripProposal> findAll();
    List<TripProposal> findByUser(Integer userId);

    List<TripProposal> findByGroup(Integer groupId);
    void update(TripProposal tripProposal);
    void delete(Integer id);
}
