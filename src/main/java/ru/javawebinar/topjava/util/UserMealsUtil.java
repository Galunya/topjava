package ru.javawebinar.topjava.util;

import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.model.UserMealWithExceed;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * GKislin
 * 31.05.2015.
 */
public class UserMealsUtil {
    public static void main(String[] args) {
        List<UserMeal> mealList = Arrays.asList(
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30, 10, 0), "Завтрак", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30, 13, 0), "Обед", 1000),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30, 20, 0), "Ужин", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31, 10, 0), "Завтрак", 1000),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31, 13, 0), "Обед", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31, 20, 0), "Ужин", 510)
        );
        getFilteredWithExceeded(mealList, LocalTime.of(7, 0), LocalTime.of(12, 0), 2000);
//        .toLocalDate();
//        .toLocalTime();
    }

    public static List<UserMealWithExceed> getFilteredWithExceeded(List<UserMeal> mealList, LocalTime startTime, LocalTime endTime, int caloriesPerDay) {

        List<UserMealWithExceed> result = new ArrayList<>();
        LocalDateTime mealDate;

        UserMealWithExceed dt;
        UserMeal meal;
        int calories = 0;
        for (int i = 0; i < mealList.size(); i++) {
            mealDate = mealList.get(i).getDateTime();
            meal = mealList.get(i);
            calories += meal.getCalories();

            if (mealDate.toLocalTime().compareTo(startTime) >= 0 && mealDate.toLocalTime().compareTo(endTime) <= 0) {
                dt = new UserMealWithExceed(mealDate, meal.getDescription(), meal.getCalories(), false);
                result.add(dt);
            }
            if (!result.isEmpty() && calories > caloriesPerDay) {
                result.get(0).setExceed(true);
            }
        }


        return result;
    }
}
