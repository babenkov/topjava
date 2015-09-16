package ru.javawebinar.topjava.web;

import ru.javawebinar.topjava.LoggerWrapper;
import ru.javawebinar.topjava.dao.UserMealDao;
import ru.javawebinar.topjava.model.UserMealWithExceed;
import ru.javawebinar.topjava.util.UserMealsUtil;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * User: gkislin
 * Date: 19.08.2014
 */
public class MealDeleteServlet extends HttpServlet {
    public static final String ATTRIBUTE_MODEL_TO_VIEW = "mealList";
    public static final String PAGE_OK = "mealList.jsp";
    public static final String PAGE_ERROR = "error.jsp";

    private static final LoggerWrapper LOG = LoggerWrapper.get(MealDeleteServlet.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        LOG.debug("delete and forward to mealList");

        String url = request.getRequestURL().toString();
        System.out.println(url);
//        try {
        String[] parts = url.split("/");
//        int id = Integer.parseInt(parts[1]);
        long id = Long.parseLong(parts[1]);
        UserMealDao.delete(id);
        try {
            List<UserMealWithExceed> model = UserMealsUtil.getFilteredMealsWithExceeded();
            request.setAttribute(ATTRIBUTE_MODEL_TO_VIEW, model);
            //OK
            response.sendRedirect(PAGE_OK);
//            RequestDispatcher dispatcher = request.getRequestDispatcher(PAGE_OK);
//            dispatcher.forward(request, response);
            return;
        }catch (Exception ignore){ /*NOP*/ }
        //fail
        response.sendRedirect(PAGE_ERROR);

    }
}
