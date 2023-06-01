<%@ page import="Menegers.Users.AdminManager" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="Menegers.DataBase.DataManager" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    int userid = Integer.parseInt(session.getAttribute("UserId").toString());
    AdminManager admin = new AdminManager();
    admin.getUserById(userid);
    ArrayList<Integer> lessons = admin.getLessons();
%>

<html>
<head>
    <title>Предмети</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
</head>
<body>
    <div><a href="<%=request.getContextPath()%>\">Повернутися на головну</a></div>
    <form method="post" action="<%=request.getContextPath()%>\admin\changelesson">
        <input type="text" name="lessonname" placeholder="Назва предмету"/>
        <button type="submit" name="what" value="add">Додати</button>
    </form>
    <ul>
    <%if(lessons!=null){
        for(int i = 0; i < lessons.size(); i++){%>
            <li>
                <form action="<%=request.getContextPath()%>\admin\changelesson?lessonid=<%=lessons.get(i)%>" method="post">
                    <%=lessons.get(i)+" "+DataManager.getLessonById(lessons.get(i))%>
                    <button type="submit" name="what" value="delete"> видалити</button>
                </form>
            </li>
    <%}}%>
    </ul>
</body>
</html>
