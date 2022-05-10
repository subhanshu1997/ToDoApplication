<%-- 
    Document   : home
    Created on : 8 May, 2022, 4:06:56 PM
    Author     : bigas
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Existing tasks</h1>
        <table border = "3">
            <tr>
                <td><b> ID </b></td>
                <td><b> Username </b></td>
                <td><b> Taskname </b></td>
                <td><b> Description </b></td>
                <td><b> LastUpdate </b></td>
                <td><b> Completed </b></td>
                <td><b> Remove Task </b></td>
            </tr>
            
    <c:forEach var="task" items="${tasks}">
        <tr>
        <td>${task.id}</td>
        <td>${task.username}</td>
        <td>${task.taskname}</td>
        <td>${task.description}</td>
        <td>${task.lastUpdate}</td>
        <td><input type="checkbox" value="${task.check}"></td>
        <td><form action="remove">    
        <button type="submit" name="remove" value="${task.id}">Remove</button>
        </form>
        </td>
        </tr>
    </c:forEach>
        </table>
        <br><br><br><br><br>
        <h1>Add task</h1>
        <form method="post" action="/home">
        <div class="container">
          <label ><b>TaskName</b></label>
          <input type="text" placeholder="Enter Taskname" name="taskname" required="">
          <label ><b>Description</b></label>
          <input type="text" placeholder="Enter Description" name="description" required="">
          <button type="submit">Add Task</button>
        </div>
</form>
    </body>
</html>
