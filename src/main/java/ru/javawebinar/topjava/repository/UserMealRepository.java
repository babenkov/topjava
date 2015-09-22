package ru.javawebinar.topjava.repository;

import ru.javawebinar.topjava.model.UserMeal;

import java.util.Collection;
import java.util.List;

/**
 * GKislin
 * 06.03.2015.
 */
public interface UserMealRepository {
    UserMeal save(UserMeal userMeal, int ownerId);

    boolean delete(int id, int ownerId);

    UserMeal get(int id, int ownerId);

    Collection<UserMeal> getAll(int ownerId);
}
