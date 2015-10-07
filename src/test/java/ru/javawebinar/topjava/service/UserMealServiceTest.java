package ru.javawebinar.topjava.service;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.rules.TestRule;
import org.junit.rules.Timeout;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Timed;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ru.javawebinar.topjava.MealTestData;
import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.time.LocalDate;
import java.time.Month;
import java.util.Arrays;

import static ru.javawebinar.topjava.MealTestData.*;
import static ru.javawebinar.topjava.UserTestData.ADMIN_ID;
import static ru.javawebinar.topjava.UserTestData.USER_ID;

@ContextConfiguration({
        "classpath:spring/spring-app.xml",
        "classpath:spring/spring-db.xml"
})
@RunWith(SpringJUnit4ClassRunner.class)
@Sql(scripts = "classpath:db/populateDB.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, config = @SqlConfig(encoding = "UTF-8"))
public class UserMealServiceTest {

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Rule
    public TestRule timeout = new Timeout(10000);

    @Autowired
    protected UserMealService service;

    @Test
    @Timed(millis = 100)
    public void testDelete() throws Exception {
        service.delete(MealTestData.MEAL1_ID, USER_ID);
        MATCHER.assertCollectionEquals(Arrays.asList(MEAL6, MEAL5, MEAL4, MEAL3, MEAL2), service.getAll(USER_ID));
    }

    @Test(expected = NotFoundException.class)
    @Timed(millis = 500)
    public void testDeleteNotFound() throws Exception {
        service.delete(MEAL1_ID, 1);
    }

    @Test
    @Timed(millis = 100)
    public void testSave() throws Exception {
        UserMeal created = getCreated();
        service.save(created, USER_ID);
        MATCHER.assertCollectionEquals(Arrays.asList(created, MEAL6, MEAL5, MEAL4, MEAL3, MEAL2, MEAL1), service.getAll(USER_ID));
    }

    @Test
    @Timed(millis = 300)
    public void testGet() throws Exception {
        UserMeal actual = service.get(ADMIN_MEAL_ID, ADMIN_ID);
        MATCHER.assertEquals(ADMIN_MEAL, actual);
    }

    @Test(expected = NotFoundException.class)
    @Timed(millis = 300)
    public void testGetNotFound() throws Exception {
        service.get(MEAL1_ID, ADMIN_ID);
    }

    @Test
    @Timed(millis = 300)
    public void testUpdate() throws Exception {
        UserMeal updated = getUpdated();
        service.update(updated, USER_ID);
        MATCHER.assertEquals(updated, service.get(MEAL1_ID, USER_ID));
    }

    @Test(expected = NotFoundException.class)
    @Timed(millis = 300)
    public void testNotFoundUpdate() throws Exception {
        UserMeal item = service.get(MEAL1_ID, USER_ID);
        service.update(item, ADMIN_ID);
    }

    @Test
    @Timed(millis = 300)
    public void testGetAll() throws Exception {
        MATCHER.assertCollectionEquals(USER_MEALS, service.getAll(USER_ID));
    }

    @Test
    @Timed(millis = 500)
    public void testGetBetween() throws Exception {
        MATCHER.assertCollectionEquals(Arrays.asList(MEAL3, MEAL2, MEAL1),
                service.getBetweenDates(LocalDate.of(2015, Month.MAY, 30), LocalDate.of(2015, Month.MAY, 30), USER_ID));
    }
}