package ru.javawebinar.topjava.repository.jpa;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.repository.UserMealRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDateTime;
import java.util.List;

/**
 * User: gkisline
 * Date: 26.08.2014
 */

@Repository
@Transactional(readOnly = true)
public class JpaUserMealRepositoryImpl implements UserMealRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
    public UserMeal save(UserMeal userMeal, int userId) {
        User ref = entityManager.getReference(User.class, userId);
        if (userMeal.isNew()) {
            userMeal.setUser(ref);
            entityManager.persist(userMeal);
            return userMeal;
        } else {
            UserMeal meal = entityManager.getReference(UserMeal.class, userMeal.getId());
            if (meal.getUser().getId() == userId) {
                userMeal.setUser(ref);
                entityManager.merge(userMeal);
                return userMeal;
            }
            return null;
        }
    }

    @Override
    @Transactional
    public boolean delete(int id, int userId) {
        return entityManager.createNamedQuery(UserMeal.DELETE)
                .setParameter(1, userId)
                .setParameter(2, id)
                .executeUpdate() != 0;
    }

    @Override
    public UserMeal get(int id, int userId) {
        UserMeal userMeal = entityManager.find(UserMeal.class, id);
        if (userMeal.getUser().getId() == userId) {
            return userMeal;
        } else {
            return null;
        }
    }

    @Override
    public List<UserMeal> getAll(int userId) {
        return entityManager.createNamedQuery(UserMeal.ALL_SORTED, UserMeal.class).
                setParameter("userId", userId).getResultList();
    }

    @Override
    public List<UserMeal> getBetween(LocalDateTime startDate, LocalDateTime endDate, int userId) {
        return entityManager.createNamedQuery(UserMeal.GET_BETWEEN, UserMeal.class)
                .setParameter(1, userId)
                .setParameter(2, startDate)
                .setParameter(3, endDate)
                .getResultList();
    }
}