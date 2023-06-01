<%@ page import="Menegers.Users.AdminManager" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="Menegers.Users.UserManager" %>
<%@ page import="Menegers.Users.TeacherManager" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    int userid = Integer.parseInt(session.getAttribute("UserId").toString());
    TeacherManager admin = new TeacherManager();
    admin.getUserById(userid);
%>
<html>
<head>
    <title>Учитель</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
</head>
<body>
    <h2>Вітаю, <%=admin.getName()%>. Ви увійшли в ролі вчителя.</h2>
    <h3>Доступні дії: </h3>
    <ul>
        <li><a href="<%=request.getContextPath()%>\teacher\tests.jsp">Список тестів</a></li>
        <li><a href="<%=request.getContextPath()%>\teacher\newtest.jsp">Додати тест</a></li>
        <li><a href="<%=request.getContextPath()%>\teacher\results.jsp">Результати тестувань</a></li>
    </ul>
    <form action="<%=request.getContextPath()%>/login" method="delete">
        <button type="submit">Вийти</button>
    </form>
</body>
</html>
