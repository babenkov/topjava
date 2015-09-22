package ru.javawebinar.topjava.repository.mock;

import org.springframework.stereotype.Repository;
import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.repository.UserMealRepository;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

/**
 * GKislin
 * 15.09.2015.
 */
@Repository
public class InMemoryUserMealRepository implements UserMealRepository {
    private Map<Integer, UserMeal> repository = new ConcurrentHashMap<>();
    private AtomicInteger counter = new AtomicInteger(0);

    {
        save(new UserMeal(LocalDateTime.of(2015, Month.MAY, 30, 10, 0), "Завтрак", 500), 0);
        save(new UserMeal(LocalDateTime.of(2015, Month.MAY, 30, 13, 0), "Обед", 1000), 0);
        save(new UserMeal(LocalDateTime.of(2015, Month.MAY, 30, 20, 0), "Ужин", 500), 0);
        save(new UserMeal(LocalDateTime.of(2015, Month.MAY, 31, 10, 0), "Завтрак", 1000), 0);
        save(new UserMeal(LocalDateTime.of(2015, Month.MAY, 31, 13, 0), "Обед", 500), 0);
        save(new UserMeal(LocalDateTime.of(2015, Month.MAY, 31, 20, 0), "Ужин", 510), 0);
        save(new UserMeal(LocalDateTime.of(2015, Month.MAY, 10, 10, 0), "Завтрак of user 1", 700), 1);
        save(new UserMeal(LocalDateTime.of(2015, Month.MAY, 10, 13, 0), "Обед of user 1", 800), 1);
        save(new UserMeal(LocalDateTime.of(2015, Month.MAY, 10, 20, 0), "Ужин of user 1", 900), 1);
    }



    @Override
    public UserMeal save(UserMeal userMeal, int ownerId) {
        if (userMeal.isNew()) {
            userMeal.setId(counter.incrementAndGet());
        }
        userMeal.setOwnerId(ownerId);
        return repository.put(userMeal.getId(), userMeal);
    }

    @Override
    public boolean delete(int id, int ownerId) {
        if( repository.get(id).getOwnerId() == ownerId) {
            repository.remove(id);
            return true;
        } else {
            throw new NotFoundException("Not found meal to delete with id=" + id);
        }
    }

    @Override
    public UserMeal get(int id, int ownerId) {
        if( repository.get(id).getOwnerId() == ownerId) {
            return repository.get(id);
        }else {
            throw new NotFoundException("Not found meal to get with id=" + id);
        }
    }

    @Override
    public Collection<UserMeal> getAll(int ownerId) {
        return repository.values().stream().filter(id -> (id.getOwnerId()==ownerId)).collect(Collectors.toList());
    }
}

