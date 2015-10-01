package ru.javawebinar.topjava.service;

import com.sun.org.apache.xpath.internal.SourceTree;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ru.javawebinar.topjava.UserTestData;
import ru.javawebinar.topjava.model.Role;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.util.DbPopulator;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.*;

import static org.junit.Assert.*;
import static ru.javawebinar.topjava.MealTestData.MATCHER;
import static ru.javawebinar.topjava.MealTestData.adminMeals;
import static ru.javawebinar.topjava.MealTestData.userMeals;
import static ru.javawebinar.topjava.UserTestData.ADMIN;
import static ru.javawebinar.topjava.UserTestData.USER;
import static ru.javawebinar.topjava.UserTestData.USER_ID;

/**
 * Created by Home7 on 30.09.2015.
 */

@ContextConfiguration({
        "classpath:spring/spring-app.xml",
        "classpath:spring/spring-db.xml"
})
@RunWith(SpringJUnit4ClassRunner.class)
public class UserMealServiceTest {
    @Autowired
    protected UserMealService service;

    @Autowired
    private DbPopulator dbPopulator;

    @Before
    public void setUp() throws Exception {
        dbPopulator.execute();
    }

    @Test
    public void testSave() throws Exception {
        UserMeal um = new UserMeal(100120,LocalDateTime.parse("2015-07-11T11:27:34"), "Завтрак пользователя", 1000);
        UserMeal created = service.save(um, 100001);
//        um.setId(created.getId());
        MATCHER.assertCollectionEquals(Arrays.asList(adminMeals.get(0), adminMeals.get(1), um, adminMeals.get(2)), service.getAll(100001));
    }

    @Test
    public void testGet() throws Exception {
        UserMeal receivedMeal = service.get(100110, 100001);
        UserMeal expected = adminMeals.get(0);

        MATCHER.assertEquals(expected, receivedMeal);
    }
    @Test
    public void testGetAll() throws Exception {
        MATCHER.assertCollectionEquals(adminMeals, service.getAll(100001));
    }

    @Test
    public void testGetBetweenDates() throws Exception {

    }

    @Test
    public void testGetBetweenDateTimes() throws Exception {

    }

    @Test
    public void testUpdate() throws Exception {
        UserMeal updated = adminMeals.get(0);
        updated.setDateTime(Timestamp.valueOf(LocalDateTime.now()));
        updated.setDescription("!!!!!");
        updated.setCalories(555);
//        updated.setId(111111);
        service.update(updated, 100001);
        System.out.println("!!!!!!!!!!" + updated + " \n" + service.getAll(100001));
        MATCHER.assertEquals(updated, service.get(100110, 100001));
    }


    @Test
    public void testDelete() throws Exception {
        service.delete(100110, 100001);
        ArrayList<UserMeal> list = new ArrayList<>(adminMeals);
        list.remove(0);
//        System.out.println("!!!!!!!!!!!!!" + list + " " + service.getAll(100001));
        assertEquals(list, service.getAll(100001));
    }
}