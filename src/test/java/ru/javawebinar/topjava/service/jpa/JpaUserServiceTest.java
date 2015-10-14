package ru.javawebinar.topjava.service.jpa;

import org.springframework.test.context.ActiveProfiles;
import ru.javawebinar.topjava.service.UserServiceTest;

import static ru.javawebinar.topjava.Profiles.JPA;

/**
 * Created by Home7 on 14.10.2015.
 */
@ActiveProfiles(JPA)
public class JpaUserServiceTest extends UserServiceTest {
}
