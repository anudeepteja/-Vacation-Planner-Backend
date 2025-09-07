package com.vacation.vacationPlanner.dao;

import com.vacation.vacationPlanner.entity.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Repository
@Transactional
public class UserDAOImpl implements UserDAO {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public User save(User user) {
        if (user.getUserId() == null) {
            entityManager.persist(user); // Insert
            return user;
        } else {
            return entityManager.merge(user); // Update
        }
    }

    @Override
    public Optional<User> findById(Long id) {
        User user = entityManager.find(User.class, id);
        return Optional.ofNullable(user);
    }

    @Override
    public List<User> findAll() {
        return entityManager.createQuery("FROM User", User.class).getResultList();
    }

    @Override
    public void deleteById(Long id) {
        User user = entityManager.find(User.class, id);
        if (user != null) {
            entityManager.remove(user);
        }
    }

    @Override
    public Optional<User> findByUsernameOrEmail(String usernameOrEmail) {
        List<User> result = entityManager.createQuery(
                        "FROM User u WHERE u.username = :value OR u.email = :value", User.class)
                .setParameter("value", usernameOrEmail)
                .getResultList();

        return result.stream().findFirst();
    }

    @Override
    public Optional<User> findByUsername(String username) {
        List<User> result = entityManager.createQuery(
                        "FROM User u WHERE u.username = :username", User.class)
                .setParameter("username", username)
                .getResultList();

        return result.stream().findFirst();
    }


}