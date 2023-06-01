<%@ page import="Menegers.Users.AdminManager" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="Menegers.Users.UserManager" %>
<%@ page import="Menegers.Users.TeacherManager" %>
<%@ page import="Menegers.Users.StudentManager" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    int userid = Integer.parseInt(session.getAttribute("UserId").toString());
    StudentManager admin = new StudentManager();
    admin.getStudentById(userid);
%>
<html>
<head>
    <title>Учень</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
</head>
<body>
    <h2>Вітаю, <%=admin.getName()%>. Ви увійшли в ролі учня.</h2>
    <h3>Доступні дії: </h3>
    <ul>
        <li><a href="<%=request.getContextPath()%>\admin\users.jsp">Список користувачів</a></li>
    </ul>
    <form action="<%=request.getContextPath()%>/login" method="delete">
        <button type="submit">Вийти</button>
    </form>
</body>
</html>
