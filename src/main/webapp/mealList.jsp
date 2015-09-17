<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.List" %>
<%@ page import="ru.javawebinar.topjava.util.UserMealsUtil" %>
<%@ page import="ru.javawebinar.topjava.model.UserMealWithExceed" %>
<%@ page import="java.time.LocalTime" %>

<html>
<head>
    <title>Meal list</title>
    <style>
        .green{color: green}
        .red{color: red}
    </style>
</head>
<body>

<h2>Meal list</h2>
<br>

<table border=1 cellpadding=3 cellspacing=0>
    <tr>
        <th>Date and Time</th>
        <th>Description</th>
        <th>Calories</th>
        <th>Delete</th>
    </tr>

<c:forEach items="${mealList}" var="meal">
    <c:set var="id" value="${mealList.indexOf(meal)}"/>
    <tr class="${meal.exceed ? 'red' : 'green'}">
    <td><c:out value="${meal.date}"/> <c:out value="${meal.time}"/> </td>
    <td><c:out value="${meal.description}"/>  </td>
    <td><c:out value="${meal.calories}"/>  </td>
    <%--<td><c:>  </td>--%>
    <%--<td><c:out value="${meal.id}"/>  </td>--%>
    <td><a href="./meals/delete/${id}">Delete</a> </td>
    </tr>
    </c:forEach>
</table>
    <br>
    <br>
<table border=1 cellpadding=5 cellspacing=0>
    <tr>
        <thead align="center"><h2>List of Meals</h2></thead>
    </tr>
    <tr>
        <th>DateTime</th>
        <th>Description</th>
        <th>Calories</th>

    </tr>
    <%
        List<UserMealWithExceed> mealList = UserMealsUtil.getFilteredMealsWithExceeded((UserMealsUtil.testList()), LocalTime.of(0, 0), LocalTime.of(23, 59), 2000);
//        List<UserMealWithExceed> mealList = (List<UserMealWithExceed>)request.getAttribute("mealList");
        for (UserMealWithExceed userMeal: mealList) {
    %>
    <tr>
        <td><span style="color:#<%=UserMealsUtil.color(userMeal)%>"><%= userMeal.getDateTime().toLocalDate()+" "+ userMeal.getDateTime().toLocalTime()%></span></td>
        <td><span style="color:#<%=UserMealsUtil.color(userMeal)%>"><%= userMeal.getDescription() %></span></td>
        <td><span style="color:#<%=UserMealsUtil.color(userMeal)%>"><%= userMeal.getCalories() %></span></td>
    </tr>
    <%
        }
    %>

</table>

<a href="/topjava">Index</a>
</body>
</html>
