package ru.javawebinar.topjava.service.jdbc;

import org.springframework.test.context.ActiveProfiles;
import ru.javawebinar.topjava.service.UserMealServiceTest;

import static ru.javawebinar.topjava.Profiles.JDBC;

/**
 * Created by Home7 on 14.10.2015.
 */
@ActiveProfiles(JDBC)
public class JdbcUserMealServiceTest extends UserMealServiceTest {
}
