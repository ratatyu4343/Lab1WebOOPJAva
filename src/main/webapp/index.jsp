<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="Menegers.Users.UserManager" %>
<%
    UserManager user = new UserManager();
    if(session.getAttribute("UserId") != null)
        user.getUserById(Integer.parseInt(session.getAttribute("UserId").toString()));
    String role = session.getAttribute("Role").toString();
    String ukrRole = "";
    switch (role){
        case "admin" :
            ukrRole = "адміністратора";
            break;
        case "student" :
            ukrRole = "учня";
            break;
        case "teacher" :
            ukrRole = "викладача";
            break;
    };
%>
<html>
<head>
    <title>Головна</title>
</head>
<body>
    <h2>Вітаємо, <%=user.getName()%></h2>
    <h3>Ви увійшли в систему "МоїТести" в ролі <%=ukrRole%></h3>
</body>
</html>
