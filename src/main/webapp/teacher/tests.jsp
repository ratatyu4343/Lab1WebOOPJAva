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
