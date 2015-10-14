package ru.javawebinar.topjava.service.datajpa;

import org.springframework.test.context.ActiveProfiles;
import ru.javawebinar.topjava.Profiles;
import ru.javawebinar.topjava.service.UserMealServiceTest;

import static ru.javawebinar.topjava.Profiles.DATAJPA;

/**
 * Created by Home7 on 14.10.2015.
 */
@ActiveProfiles(DATAJPA)
public class DataJpaUserMealServiceTest extends UserMealServiceTest {
}
