package ru.javawebinar.topjava.util;

import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.model.UserMealWithExceed;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.*;
import java.util.stream.Collectors;

import static ru.javawebinar.topjava.util.TimeUtil.isBetween;
import static ru.javawebinar.topjava.util.TimeUtil.toLocalDate;
import static ru.javawebinar.topjava.util.TimeUtil.toLocalTime;

/**
 * GKislin
 * 31.05.2015.
 */
public class UserMealsUtil {
    public static void main(String[] args) {
        List<UserMeal> mealList = Arrays.asList(
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30, 10, 0), "Завтрак", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30, 13, 0), "Обед", 1000),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30, 20, 0), "Ужин", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31, 10, 0), "Завтрак", 1000),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31, 13, 0), "Обед", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31, 20, 0), "Ужин", 510)
        );
        getFilteredMealsWithExceeded(mealList, LocalTime.of(7, 0), LocalTime.of(12, 0), 2000).
                stream().forEach(System.out::println);

    }

    public static List<UserMealWithExceed> getFilteredMealsWithExceeded(List<UserMeal> mealList, LocalTime startTime, LocalTime endTime, int caloriesPerDay) {

        //list to map with key = date, value = sumCaloriesByDay
        Map<LocalDate, Integer> mapDateCalories = mealList.
                stream().
                collect(Collectors.groupingBy(c -> c.getDateTime().toLocalDate(), Collectors.summingInt(UserMeal::getCalories)));
        mapDateCalories.forEach((date, val) -> System.out.println(date + " " + val));

        //filtering by time
        return mealList.stream().
                filter(s -> isBetween(s.getDateTime().toLocalTime(), startTime, endTime)).
                map(s -> new UserMealWithExceed(
                                s.getDateTime(), s.getDescription(), s.getCalories(),
                                mapDateCalories.get(s.getDateTime().toLocalDate()) > caloriesPerDay)
                ).collect(Collectors.toList());


//        List<UserMealWithExceed> exceedList = new ArrayList<>();
//        //list to map with key = date, value = sumCaloriesByDay
//        Map<LocalDate, List<UserMeal>> mapByDate = new HashMap<>();
//        for (UserMeal userMeal: mealList) {
//            LocalDate localDate = toLocalDate(userMeal.getDateTime());
//            List<UserMeal> dayMealList = mapByDate.get(localDate);
//            if (dayMealList == null){
//                dayMealList = new ArrayList<>();
//                mapByDate.put(localDate, dayMealList);
//            }
//            dayMealList.add(userMeal);
//        }
//
//        //map to list by dates
//        for (Map.Entry<LocalDate, List<UserMeal>> dateEntry : mapByDate.entrySet()) {
//            int calories = 0;
//            List<UserMeal> userMealList = dateEntry.getValue();
//            for (UserMeal userMeal: userMealList){
//                calories = calories + userMeal.getCalories();
//            }
//            //filtering
//            for (UserMeal userMeal : userMealList) {
//                if (TimeUtil.isBetween(toLocalTime(userMeal.getDateTime()), startTime, endTime)) {
//                    if ((calories > caloriesPerDay)) {
//                        exceedList.add(new UserMealWithExceed(userMeal.getDateTime(), userMeal.getDescription(), userMeal.getCalories(), true));
//                    } else {
//                        exceedList.add(new UserMealWithExceed(userMeal.getDateTime(), userMeal.getDescription(), userMeal.getCalories(), false));
//                    }
//                }
//            }
//        }
//        return exceedList;
    }
}