package ru.javawebinar.topjava.dao;


import ru.javawebinar.topjava.model.UserMealWithExceed;
import ru.javawebinar.topjava.util.UserMealsUtil;

import java.util.List;

public class UserMealDao {
    private static final List<UserMealWithExceed> mealList = UserMealsUtil.getFilteredMealsWithExceeded();

    public static List<UserMealWithExceed> selectAll()
    {
        return mealList;
    }
    public static UserMealWithExceed selectById(int id)
    {
        return mealList.get(id);
    }

    public static void create(UserMealWithExceed meal)
    {
        mealList.add(meal);
    }

    public static void update(int id, UserMealWithExceed meal)
    {
        mealList.remove(id);
        mealList.add(id, meal);
    }

    public static void delete(int id)
    {
        mealList.remove(id);
    }
}
