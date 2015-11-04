package ru.javawebinar.topjava.web.meal;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.javawebinar.topjava.model.Role;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.to.UserMealWithExceed;
import ru.javawebinar.topjava.util.TimeUtil;

import java.net.URI;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping(value = UserMealAjaxController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class UserMealAjaxController extends AbstractUserMealController {
    public static final String REST_URL = "/ajax/profile/meals";

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<UserMealWithExceed> getAll() {
        return super.getAll();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable("id") Integer id) {
        super.delete(id);
    }

    @RequestMapping(method = RequestMethod.POST)
    public void update(@RequestParam("id") Integer id,
                       @RequestParam("dateTime") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dateTime,
                       @RequestParam("description") String description,
                       @RequestParam("calories") Integer calories) {
        UserMeal userMeal = new UserMeal(dateTime, description, calories);
        if (id == 0) {
            super.create(userMeal);
        } else {
            super.update(userMeal, id);
        }
    }
}
