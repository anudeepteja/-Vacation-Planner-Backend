package com.vacation.vacationPlanner.dao;

import com.vacation.vacationPlanner.entity.UserGroup;
import com.vacation.vacationPlanner.entity.UserGroupIdCK;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Transactional
public class UserGroupDAOImpl implements UserGroupDAO {

    @PersistenceContext
    private EntityManager entityManager;


    @Override
    public void save(UserGroup userGroup) {
        entityManager.persist(userGroup);
    }

    @Override
    public UserGroup findById(UserGroupIdCK id) {
        return entityManager.find(UserGroup.class, id);
    }

    @Override
    public List<UserGroup> findAll() {
        return entityManager.createQuery("FROM UserGroup", UserGroup.class)
                .getResultList();
    }

    @Override
    public List<UserGroup> findByUser(Integer userId) {
        return entityManager.createQuery(
                        "SELECT ug FROM UserGroup ug WHERE ug.user.id = :userId", UserGroup.class)
                .setParameter("userId", userId)
                .getResultList();
    }

    @Override
    public List<UserGroup> findByGroup(Integer groupId) {
        return entityManager.createQuery(
                        "SELECT ug FROM UserGroup ug WHERE ug.group.id = :groupId", UserGroup.class)
                .setParameter("groupId", groupId)
                .getResultList();
    }

    @Override
    public void delete(UserGroupIdCK id) {
        UserGroup userGroup = findById(id);
        if (userGroup != null) {
            entityManager.remove(userGroup);
        }
    }
}
