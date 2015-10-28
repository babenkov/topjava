package ru.javawebinar.topjava.web.meal;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.javawebinar.topjava.LoggedUser;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.to.UserMealWithExceed;
import ru.javawebinar.topjava.util.TimeUtil;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static java.time.format.DateTimeFormatter.ISO_LOCAL_DATE_TIME;

/**
 * GKislin
 * 06.03.2015.
 */
@RestController
@RequestMapping(UserMealRestController.REST_URL)
public class UserMealRestController extends AbstractUserMealController {
    public static final String REST_URL = "/rest/meals";

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public UserMeal get(@PathVariable("id") int id) {
        return super.get(id);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable("id") int id) {
        super.delete(id);
    }

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<UserMealWithExceed> getAll() {
        return super.getAll();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    public void update(@RequestBody UserMeal um) {
        super.update(um);
    }

//    @RequestMapping(value = "/{id}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
//    public UserMeal create(@RequestBody UserMeal um){
//        return super.create(um);
//    }
//
    @RequestMapping(value = "/{sdt}/{edt}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<UserMealWithExceed> getBetween(@PathVariable("sdt") String sdt, @PathVariable("edt") String edt) {
        return super.getBetween(TimeUtil.parseLocalDateTime(sdt, ISO_LOCAL_DATE_TIME).toLocalDate(),
                TimeUtil.parseLocalDateTime(sdt, ISO_LOCAL_DATE_TIME).toLocalTime(),
                TimeUtil.parseLocalDateTime(edt, ISO_LOCAL_DATE_TIME).toLocalDate(),
                TimeUtil.parseLocalDateTime(edt, ISO_LOCAL_DATE_TIME).toLocalTime());
    }
}