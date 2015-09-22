package ru.javawebinar.topjava.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.repository.UserMealRepository;
import ru.javawebinar.topjava.util.exception.ExceptionUtil;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

/**
 * GKislin
 * 06.03.2015.
 */
@Service
public class UserMealServiceImpl implements UserMealService {

    @Autowired
    private UserMealRepository repository;

    @Override
    public UserMeal create(UserMeal userMeal, int ownerId) {
        return repository.save(userMeal, ownerId);
    }

    @Override
    public UserMeal get(int id, int ownerId) throws NotFoundException {
        return repository.get(id, ownerId);
    }

    @Override
    public void update(UserMeal userMeal, int ownerId) throws NotFoundException {
        repository.save(userMeal, ownerId);
    }

    @Override
    public void delete(int id, int ownerId) throws NotFoundException {
        ExceptionUtil.check(repository.delete(id, ownerId), id);
    }

    @Override
    public Collection<UserMeal> getAll(int ownerId) {
        return repository.getAll(ownerId);
    }

}
