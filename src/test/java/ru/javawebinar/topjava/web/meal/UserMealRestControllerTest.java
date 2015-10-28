package ru.javawebinar.topjava.web.meal;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;
import ru.javawebinar.topjava.MealTestData;
import ru.javawebinar.topjava.TestUtil;
import ru.javawebinar.topjava.UserTestData;
import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.service.UserMealService;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import ru.javawebinar.topjava.to.UserMealWithExceed;
import ru.javawebinar.topjava.util.UserMealsUtil;
import ru.javawebinar.topjava.web.AbstractControllerTest;
import ru.javawebinar.topjava.web.json.JsonUtil;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static ru.javawebinar.topjava.MealTestData.*;

import static ru.javawebinar.topjava.util.UserMealsUtil.getWithExceeded;

/**
 * Created by Home7 on 28.10.2015.
 */
public class UserMealRestControllerTest extends AbstractControllerTest {

    public static final String REST_URL = UserMealRestController.REST_URL + '/';

    @Autowired
    private UserMealService service;

    @Test
    public void testGet() throws Exception {
        TestUtil.print(mockMvc.perform(get(REST_URL + MEAL1_ID))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MATCHER.contentMatcher(MEAL1)));
    }

    @Test
    public void testDelete() throws Exception {
        mockMvc.perform(delete(REST_URL + MEAL1_ID))
                .andDo(print())
                .andExpect(status().isOk());

        mockMvc.perform(get(REST_URL))
                .andExpect(MATCHER_WITH_EXCEED.contentListMatcher(
                        getWithExceeded(Arrays.asList(MEAL6, MEAL5, MEAL4, MEAL3, MEAL2),
                                UserTestData.USER.getCaloriesPerDay())
                ));
    }

    @Test
    public void testGetAll() throws Exception {

        List<UserMealWithExceed> listExpected = getWithExceeded(USER_MEALS, UserTestData.USER.getCaloriesPerDay());
        mockMvc.perform(get(REST_URL).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MATCHER_WITH_EXCEED.contentListMatcher(listExpected));
    }

    @Test
    public void testUpdate() throws Exception {
        UserMeal updated = MEAL1;
        mockMvc.perform(put(REST_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(updated)))
                .andExpect(status().isOk());

        MATCHER.assertEquals(updated, service.get(MEAL1_ID, UserTestData.USER_ID));
    }

    @Test
    public void testCreateWithLocation() throws Exception {
        UserMeal created = getCreated();
        created.setUser(UserTestData.USER);
        ResultActions action = mockMvc.perform(post(REST_URL).contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(created)))
                .andExpect(status().isCreated());

        UserMeal returned = MATCHER.fromJsonAction(action);
        created.setId(returned.getId());

        MATCHER.assertEquals(created, returned);
        MATCHER.assertCollectionEquals(Arrays.asList(created, MEAL6, MEAL5, MEAL4, MEAL3, MEAL2, MEAL1), service.getAll(UserTestData.USER_ID));

    }

    @Test
    public void testGetBetween() throws Exception {

        List<UserMealWithExceed> listExpected = getWithExceeded(Arrays.asList(MEAL6, MEAL5, MEAL4), UserTestData.USER.getCaloriesPerDay());

        TestUtil.print(
                mockMvc.perform(get(REST_URL + "filter")
                        .param("from", "2015-05-31T00:00:00")
                        .param("to", "2500-12-31T23:59:59")
                        .contentType(MediaType.APPLICATION_JSON))
                        .andDo(print())
                        .andExpect(status().isOk())
                        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                        .andExpect(MATCHER_WITH_EXCEED.contentListMatcher(listExpected)));
    }
}