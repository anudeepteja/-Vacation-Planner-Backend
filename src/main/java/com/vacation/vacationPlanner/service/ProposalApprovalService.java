package com.vacation.vacationPlanner.service;

import com.vacation.vacationPlanner.entity.ProposalApproval;
import com.vacation.vacationPlanner.entity.ProposalApprovalIdCK;

import java.util.List;

public interface ProposalApprovalService {
    void saveApproval(ProposalApproval approval);
    ProposalApproval getApprovalById(ProposalApprovalIdCK id);
    List<ProposalApproval> getAllApprovals();
    List<ProposalApproval> getApprovalsByProposalId(Integer proposalId);
    List<ProposalApproval> getApprovalsByUserId(Integer userId);
    void deleteApproval(ProposalApprovalIdCK id);
}
