package ru.javawebinar.topjava.model;

import org.hibernate.annotations.Type;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * GKislin
 * 11.01.2015.
 */
@NamedQueries({
        @NamedQuery(name = UserMeal.ALL_SORTED, query = "SELECT um FROM UserMeal um WHERE um.user.id=:userId ORDER BY um.dateTime DESC"),
        @NamedQuery(name=UserMeal.DELETE, query="DELETE FROM UserMeal um WHERE um.user.id=?1 AND um.id=?2"),
        @NamedQuery(name=UserMeal.GET_BETWEEN, query="SELECT um FROM UserMeal um WHERE um.user.id=?1 AND um.dateTime>?2 AND um.dateTime<?3 ORDER BY um.dateTime DESC"),
})

@Entity
@Table(name = "meals")
public class UserMeal extends BaseEntity {

    public static final String DELETE = "UserMeal.delete";
    public static final String ALL_SORTED = "UserMeal.getAllSorted";
    public static final String GET_BETWEEN = "UserMeal.getBetween";

//    @Column(name = "date_time", nullable = false)
//    @Convert(converter = LocalDateTimePersistenceConverter.class)

    @NotNull
    @Column(name = "date_time", nullable = false)
    protected LocalDateTime dateTime;

    @NotEmpty
    @Column(name = "description", nullable = false)
    protected String description;

    @NotEmpty
    @Column(name = "calories", nullable = false)
    protected int calories;

    @JoinColumn(name = "user_id", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    public UserMeal() {
    }

    public UserMeal(LocalDateTime dateTime, String description, int calories) {
        this(null, dateTime, description, calories);
    }

    public UserMeal(Integer id, LocalDateTime dateTime, String description, int calories) {
        super(id);
        this.dateTime = dateTime;
        this.description = description;
        this.calories = calories;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public String getDescription() {
        return description;
    }

    public int getCalories() {
        return calories;
    }

    public Integer getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setCalories(int calories) {
        this.calories = calories;
    }

    @Override
    public String toString() {
        return "UserMeal{" +
                "id=" + id +
                ", dateTime=" + dateTime +
                ", description='" + description + '\'' +
                ", calories=" + calories +
                '}';
    }
}