package ru.javawebinar.topjava.web.meal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import ru.javawebinar.topjava.LoggerWrapper;
import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.service.UserMealService;
import ru.javawebinar.topjava.service.UserMealServiceImpl;

import java.util.Collection;

/**
 * GKislin
 * 06.03.2015.
 */
@Controller
public class UserMealRestController {
    @Autowired
    private UserMealService service;
    protected final LoggerWrapper LOG = LoggerWrapper.get(getClass());

    public void update(UserMeal userMeal, int ownerId) {
        LOG.info("update meal: ", userMeal);
        service.update(userMeal, ownerId);
    }

    public UserMeal create(UserMeal userMeal, int ownerId) {
        LOG.info("create meal: ", userMeal);
        return service.create(userMeal, ownerId);
    }

    public void delete(int id, int ownerId) {
        LOG.info("delete meal: ", id);
        service.delete(id, ownerId);
    }

    public UserMeal get(int id, int ownerId) {
        LOG.info("get meal: ", id);
        return service.get(id, ownerId);
    }

    public Collection<UserMeal> getAll(int ownerId) {
        LOG.info("getAll meals");
        return service.getAll(ownerId);
    }
}
