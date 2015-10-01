package ru.javawebinar.topjava;

import ru.javawebinar.topjava.matcher.ModelMatcher;
import ru.javawebinar.topjava.model.UserMeal;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

/**
 * GKislin
 * 13.03.2015.
 */
public class MealTestData {

    public static final ModelMatcher<UserMeal, String> MATCHER = new ModelMatcher<>(UserMeal::toString);

    public static List<UserMeal> adminMeals = Arrays.asList(
            new UserMeal(100110, LocalDateTime.parse("2015-05-11T06:36:56"), "Завтрак админа", 888),
            new UserMeal(100111, LocalDateTime.parse("2015-05-11T12:46:15"), "Обед админа", 777),
            new UserMeal(100112, LocalDateTime.parse("2015-05-11T20:45:07"), "Ужин админа", 999)

    );

    public static List<UserMeal> userMeals = Arrays.asList(
            new UserMeal(100120,LocalDateTime.parse("2015-07-11T11:27:34"), "Завтрак пользователя", 1000),
            new UserMeal(100121,LocalDateTime.parse("2015-07-11T14:06:51"), "Обед пользователя", 800),
            new UserMeal(100122,LocalDateTime.parse("2015-07-11T20:42:57"), "Ужин пользователя", 700),
            new UserMeal(100123,LocalDateTime.parse("2015-07-12T10:00:00"), "Завтрак пользователя", 1001),
            new UserMeal(100124,LocalDateTime.parse("2015-07-12T14:00:00"), "Обед пользователя", 801),
            new UserMeal(100125,LocalDateTime.parse("2015-07-12T20:00:00"), "Ужин пользователя", 701)
    );


}
