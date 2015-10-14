package ru.javawebinar.topjava.service.jdbc;

import org.springframework.test.context.ActiveProfiles;
import ru.javawebinar.topjava.service.UserServiceTest;

import static ru.javawebinar.topjava.Profiles.JDBC;

/**
 * Created by Home7 on 14.10.2015.
 */
@ActiveProfiles(JDBC)
public class JdbcUserServiceTest extends UserServiceTest {
}
