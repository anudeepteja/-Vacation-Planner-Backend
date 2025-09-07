package com.vacation.vacationPlanner.dao;

import com.vacation.vacationPlanner.entity.ProposalApproval;
import com.vacation.vacationPlanner.entity.ProposalApprovalIdCK;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Transactional
public class ProposalApprovalDAOImpl implements ProposalApprovalDAO {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void save(ProposalApproval approval) {
        if (entityManager.find(ProposalApproval.class, approval.getId()) == null) {
            entityManager.persist(approval); // new entry
        } else {
            entityManager.merge(approval);   // update existing
        }
    }

    @Override
    public ProposalApproval findById(ProposalApprovalIdCK id) {
        return entityManager.find(ProposalApproval.class, id);
    }

    @Override
    public List<ProposalApproval> findAll() {
        return entityManager.createQuery("FROM ProposalApproval", ProposalApproval.class)
                .getResultList();
    }

    @Override
    public List<ProposalApproval> findByProposalId(Integer proposalId) {
        return entityManager.createQuery(
                        "SELECT p FROM ProposalApproval p WHERE p.proposal.id = :proposalId",
                        ProposalApproval.class)
                .setParameter("proposalId", proposalId)
                .getResultList();
    }

    @Override
    public List<ProposalApproval> findByUserId(Integer userId) {
        return entityManager.createQuery(
                        "SELECT p FROM ProposalApproval p WHERE p.user.userId = :userId",
                        ProposalApproval.class)
                .setParameter("userId", userId)
                .getResultList();
    }

    @Override
    public void delete(ProposalApprovalIdCK id) {
        ProposalApproval approval = findById(id);
        if (approval != null) {
            entityManager.remove(approval);
        }
    }
}
