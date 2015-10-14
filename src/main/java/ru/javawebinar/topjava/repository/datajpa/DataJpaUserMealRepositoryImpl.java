package ru.javawebinar.topjava.repository.datajpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.repository.UserMealRepository;

import java.time.LocalDateTime;
import java.util.List;

/**
 * GKislin
 * 27.03.2015.
 */
@Repository
public class DataJpaUserMealRepositoryImpl implements UserMealRepository{
//    private static final Sort SORT_DATE = new Sort("date_time");

    @Autowired
    private ProxyUserMealRepository proxy;

    @Autowired
    private ProxyUserRepository userProxy;

    @Override
    public UserMeal save(UserMeal userMeal, int userId) {
        if (!userMeal.isNew() && get(userMeal.getId(), userId) == null) {
            return null;
        }
        User ref = userProxy.getOne(userId);
        userMeal.setUser(ref);
        return proxy.save(userMeal);
    }



    @Override
    public boolean delete(int id, int userId) {
        return proxy.deleteByIdAndUserId(id, userId) != 0;
//        return proxy.delete(id, userId) != 0;
    }

    @Override
    public UserMeal get(int id, int userId) {
        return proxy.findOne(id, userId);
//        return proxy.findByIdAndUserId(id, userId);
    }

    @Override
    public List<UserMeal> getAll(int userId) {
//        return proxy.findAll(userId);
        return proxy.findByUserIdOrderByDateTimeDesc(userId);
    }

    @Override
    public List<UserMeal> getBetween(LocalDateTime startDate, LocalDateTime endDate, int userId) {
//        return proxy.findBetween(userId, startDate, endDate);
        return proxy.findByUserIdAndDateTimeBetweenOrderByDateTimeDesc(userId, startDate, endDate);
    }
}
