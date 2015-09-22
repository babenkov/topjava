package ru.javawebinar.topjava.service;

import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.util.Collection;
import java.util.List;

/**
 * GKislin
 * 15.06.2015.
 */
public interface UserMealService {

    UserMeal create(UserMeal userMeal, int ownerId);

    UserMeal get(int id, int ownerId) throws NotFoundException;

    void update(UserMeal userMeal, int ownerId) throws NotFoundException;

    void delete(int id, int ownerId) throws NotFoundException;

    Collection<UserMeal> getAll(int ownerId);

}
