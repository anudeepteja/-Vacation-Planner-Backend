package com.vacation.vacationPlanner.dao;
import com.vacation.vacationPlanner.entity.TripProposal;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
@Transactional
public class TripProposalDAOImpl implements TripProposalDAO {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void save(TripProposal tripProposal) {
        entityManager.persist(tripProposal);
    }

    @Override
    public TripProposal findById(Integer id) {
        return entityManager.find(TripProposal.class, id);
    }

    @Override
    public List<TripProposal> findAll() {
        return entityManager.createQuery("FROM TripProposal", TripProposal.class).getResultList();
    }

    @Override
    public List<TripProposal> findByUser(Integer userId) {
        return entityManager.createQuery(
                        "SELECT t FROM TripProposal t WHERE t.proposedBy.userId = :userId", TripProposal.class)
                .setParameter("userId", userId)
                .getResultList();
    }

    @Override
    public List<TripProposal> findByGroup(Integer groupId) {
        return entityManager.createQuery(
                        "SELECT t FROM TripProposal t WHERE t.group.groupId = :groupId", TripProposal.class)
                .setParameter("groupId", groupId)
                .getResultList();
    }

    @Override
    public void update(TripProposal tripProposal) {
        entityManager.merge(tripProposal);
    }

    @Override
    public void delete(Integer id) {
        TripProposal tp = findById(id);
        if (tp != null) {
            entityManager.remove(tp);
        }
    }
}
