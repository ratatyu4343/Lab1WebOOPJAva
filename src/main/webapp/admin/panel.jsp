<%@ page import="Menegers.Users.AdminManager" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="Menegers.Users.UserManager" %>
<%@ page import="Menegers.Users.TeacherManager" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    int userid = Integer.parseInt(session.getAttribute("UserId").toString());
    AdminManager admin = new AdminManager();
    admin.getUserById(userid);
%>
<html>
<head>
    <title>Адміністратор</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
</head>
<body>
    <h2>Вітаю, <%=admin.getName()%>. Ви увійшли в ролі адміністратора.</h2>
    <h3>Доступні опції: </h3>
    <ul>
        <li><a href="<%=request.getContextPath()%>\admin\users.jsp">Список користувачів</a></li>
        <li><a href="<%=request.getContextPath()%>\admin\lessons.jsp">Список предметів</a></li>
        <li><a href="<%=request.getContextPath()%>\admin\classes.jsp">Список класів</a></li>
    </ul>
    <form action="<%=request.getContextPath()%>/login" method="delete">
        <button type="submit">Вийти</button>
    </form>
</body>
</html>
