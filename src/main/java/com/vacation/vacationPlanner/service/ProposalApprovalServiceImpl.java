package com.vacation.vacationPlanner.service;

import com.vacation.vacationPlanner.dao.ProposalApprovalDAO;
import com.vacation.vacationPlanner.entity.ProposalApproval;
import com.vacation.vacationPlanner.entity.ProposalApprovalIdCK;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProposalApprovalServiceImpl implements ProposalApprovalService {

    private final ProposalApprovalDAO proposalApprovalDAO;

    @Autowired
    public ProposalApprovalServiceImpl(ProposalApprovalDAO proposalApprovalDAO) {
        this.proposalApprovalDAO = proposalApprovalDAO;
    }

    @Override
    public void saveApproval(ProposalApproval approval) {
        proposalApprovalDAO.save(approval);
    }

    @Override
    public ProposalApproval getApprovalById(ProposalApprovalIdCK id) {
        return proposalApprovalDAO.findById(id);
    }

    @Override
    public List<ProposalApproval> getAllApprovals() {
        return proposalApprovalDAO.findAll();
    }

    @Override
    public List<ProposalApproval> getApprovalsByProposalId(Integer proposalId) {
        return proposalApprovalDAO.findByProposalId(proposalId);
    }

    @Override
    public List<ProposalApproval> getApprovalsByUserId(Integer userId) {
        return proposalApprovalDAO.findByUserId(userId);
    }

    @Override
    public void deleteApproval(ProposalApprovalIdCK id) {
        proposalApprovalDAO.delete(id);
    }
}
