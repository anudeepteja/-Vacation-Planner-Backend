package com.vacation.vacationPlanner.dao;

import com.vacation.vacationPlanner.entity.ProposalApproval;
import com.vacation.vacationPlanner.entity.ProposalApprovalIdCK;

import java.util.List;

public interface ProposalApprovalDAO {
    void save(ProposalApproval approval);
    ProposalApproval findById(ProposalApprovalIdCK id);
    List<ProposalApproval> findAll();
    List<ProposalApproval> findByProposalId(Integer proposalId);
    List<ProposalApproval> findByUserId(Integer userId);
    void delete(ProposalApprovalIdCK id);
}
