package com.vacation.vacationPlanner.dao;

import com.vacation.vacationPlanner.entity.TripGroup;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Transactional
public class TripGroupDAOImpl implements TripGroupDAO {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void save(TripGroup tripGroup) {
        if (tripGroup.getGroupId() == null) {
            entityManager.persist(tripGroup); // new group
        } else {
            entityManager.merge(tripGroup);   // update existing group
        }
    }

    @Override
    public TripGroup findById(Integer groupId) {
        return entityManager.find(TripGroup.class, groupId);
    }

    @Override
    public List<TripGroup> findAll() {
        return entityManager.createQuery("FROM TripGroup", TripGroup.class)
                .getResultList();
    }

    @Override
    public void delete(Integer groupId) {
        TripGroup group = findById(groupId);
        if (group != null) {
            entityManager.remove(group);
        }
    }
}
