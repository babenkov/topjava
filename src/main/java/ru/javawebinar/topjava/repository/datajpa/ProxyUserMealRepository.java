package ru.javawebinar.topjava.repository.datajpa;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.model.UserMeal;

import java.time.LocalDateTime;
import java.util.List;

/**
 * GKislin
 * 27.03.2015.
 */
@Transactional(readOnly = true)
public interface ProxyUserMealRepository extends JpaRepository<UserMeal, Integer> {

    //get
    @Query("SELECT um FROM UserMeal um WHERE um.id=?1 AND um.user.id = ?2")
    UserMeal findOne(Integer id, Integer userId);

    UserMeal findByIdAndUserId(int id, int userId);

    //detele
    @Transactional
    @Modifying
    @Query("DELETE FROM UserMeal um WHERE um.id = ?1 and um.user.id = ?2")
    int delete(Integer id, Integer userId);

    @Transactional
    @Modifying
    int deleteByIdAndUserId(int id, int userId);


    //save
    @Override
    @Transactional
    UserMeal save(UserMeal user);

    //getAll
    @Query("SELECT um FROM UserMeal um WHERE um.user.id = ?1 ORDER BY um.dateTime DESC")
    @Modifying
    List<UserMeal> findAll(int userId);

    List<UserMeal> findByUserIdOrderByDateTimeDesc(int userId);


    //geBetween
    @Query("SELECT m from UserMeal m WHERE m.user.id=?1 AND m.dateTime BETWEEN ?2 AND ?3 ORDER BY m.dateTime DESC")
//    @Query("SELECT m from UserMeal m WHERE m.dateTime > ?1 AND m.dateTime < ?2 AND m.user.id = ?3 ORDER BY m.dateTime DESC")
    List<UserMeal> findBetween(int userId, LocalDateTime startDate, LocalDateTime endDate);

    List<UserMeal> findByUserIdAndDateTimeBetweenOrderByDateTimeDesc(int userId, LocalDateTime startDate, LocalDateTime endDate);

}
