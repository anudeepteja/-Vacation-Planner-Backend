package com.vacation.vacationPlanner.controller;

import com.vacation.vacationPlanner.entity.ProposalApproval;
import com.vacation.vacationPlanner.entity.ProposalApprovalIdCK;
import com.vacation.vacationPlanner.service.ProposalApprovalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/proposal-approvals")
public class ProposalApprovalController {

    private final ProposalApprovalService proposalApprovalService;

    @Autowired
    public ProposalApprovalController(ProposalApprovalService proposalApprovalService) {
        this.proposalApprovalService = proposalApprovalService;
    }

    // Create or Update
    @PostMapping
    public String saveApproval(@RequestBody ProposalApproval approval) {
        proposalApprovalService.saveApproval(approval);
        return "Proposal approval saved successfully!";
    }

    // Find by composite key (userId + proposalId)
    @GetMapping("/{userId}/{proposalId}")
    public ProposalApproval getApprovalById(@PathVariable Integer userId, @PathVariable Integer proposalId) {
        ProposalApprovalIdCK id = new ProposalApprovalIdCK(userId, proposalId);
        return proposalApprovalService.getApprovalById(id);
    }

    // Find all approvals
    @GetMapping
    public List<ProposalApproval> getAllApprovals() {
        return proposalApprovalService.getAllApprovals();
    }

    // Find approvals by proposalId
    @GetMapping("/proposal/{proposalId}")
    public List<ProposalApproval> getApprovalsByProposalId(@PathVariable Integer proposalId) {
        return proposalApprovalService.getApprovalsByProposalId(proposalId);
    }

    // Find approvals by userId
    @GetMapping("/user/{userId}")
    public List<ProposalApproval> getApprovalsByUserId(@PathVariable Integer userId) {
        return proposalApprovalService.getApprovalsByUserId(userId);
    }

    // Delete approval by composite key
    @DeleteMapping("/{userId}/{proposalId}")
    public String deleteApproval(@PathVariable Integer userId, @PathVariable Integer proposalId) {
        ProposalApprovalIdCK id = new ProposalApprovalIdCK(userId, proposalId);
        proposalApprovalService.deleteApproval(id);
        return "Proposal approval deleted successfully!";
    }
}
