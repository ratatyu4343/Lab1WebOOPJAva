<%@ page import="Menegers.Users.TeacherManager" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.Map" %>
<%@ page import="java.util.Set" %>
<%@ page import="Menegers.DataBase.DataManager" %>
<%@ page import="Menegers.Tests.TestManager" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    int userid = Integer.parseInt(session.getAttribute("UserId").toString());
    TeacherManager teacher = new TeacherManager();
    teacher.getTeacherById(userid);
%>
<html>
<head>
    <title>Тести</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
</head>
<body>
    <ul>
    <%
        ArrayList<TestManager> tests = teacher.getTests();
        for(TestManager test : tests){%>
            <li>
                <a href="<%=request.getContextPath()%>/teacher/changetest?test_id=<%=test.getId()%>">
                <%=test.getName()+" ("+DataManager.getLessonById(test.getLessonId())+")"%>
                </a>
            </li>
        <%}%>
    </ul>
</body>
</html>
