package com.vacation.vacationPlanner.service;

import com.vacation.vacationPlanner.dao.ProposalApprovalDAO;
import com.vacation.vacationPlanner.dao.TripProposalDAO;
import com.vacation.vacationPlanner.dao.UserGroupDAO;
import com.vacation.vacationPlanner.entity.ProposalApproval;
import com.vacation.vacationPlanner.entity.ProposalApprovalIdCK;
import com.vacation.vacationPlanner.entity.TripProposal;
import com.vacation.vacationPlanner.entity.UserGroup;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@Transactional
public class TripProposalServiceImpl implements TripProposalService {

    private final TripProposalDAO tripProposalDAO;
    private final UserGroupDAO userGroupDAO;
    private final ProposalApprovalDAO proposalApprovalDAO;

    // Constructor Injection
    public TripProposalServiceImpl(TripProposalDAO tripProposalDAO, UserGroupDAO userGroupDAO, ProposalApprovalDAO proposalApprovalDAO) {
        this.tripProposalDAO = tripProposalDAO;
        this.userGroupDAO = userGroupDAO;
        this.proposalApprovalDAO = proposalApprovalDAO;
    }

    @Override
    public void createTripProposal(TripProposal tripProposal) {
        tripProposalDAO.save(tripProposal);
        // Fetch all members of the group
        List<UserGroup> listOfUserGroups = userGroupDAO.findByGroup(tripProposal.getGroup().getGroupId());

        // For each member, create a ProposalApproval entry
        for (UserGroup ug : listOfUserGroups) {
            ProposalApproval approval = new ProposalApproval();
            ProposalApprovalIdCK id = new ProposalApprovalIdCK();
            id.setUserId(ug.getUser().getUserId());
            id.setProposalId(tripProposal.getId()); // <-- ID should be generated after save

            approval.setId(id);
            approval.setUser(ug.getUser());
            approval.setProposal(tripProposal);

            // If it's the same user who proposed, mark APPROVED automatically
            if (ug.getUser().getUserId().equals(tripProposal.getProposedBy().getUserId())) {
                approval.setStatus("APPROVED");
            } else {
                approval.setStatus("PENDING");
            }

            proposalApprovalDAO.save(approval);
        }
    }

    @Override
    public TripProposal getTripProposalById(Integer id) {
        return tripProposalDAO.findById(id);
    }

    @Override
    public List<TripProposal> getAllTripProposals() {
        return tripProposalDAO.findAll();
    }

    @Override
    public List<TripProposal> getTripProposalsByUser(Integer userId) {
        return tripProposalDAO.findByUser(userId);
    }

    @Override
    public List<TripProposal> getTripProposalsByGroup(Integer groupId) {
        return tripProposalDAO.findByGroup(groupId);
    }

    @Override
    public void updateTripProposal(TripProposal tripProposal) {
        tripProposalDAO.update(tripProposal);
    }

    @Override
    public void deleteTripProposal(Integer id) {
        tripProposalDAO.delete(id);
    }
}
