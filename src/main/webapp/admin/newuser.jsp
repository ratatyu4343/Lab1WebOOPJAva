<%@ page import="Menegers.Users.UserManager" %>
<%@ page import="Menegers.Users.AdminManager" %>
<%@ page import="Menegers.Users.TeacherManager" %>
<%@ page import="Menegers.Users.StudentManager" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.Map" %>
<%@ page import="java.util.Set" %>
<%@ page import="Menegers.DataBase.DataManager" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    AdminManager myManager = new AdminManager();
    String currentRole = request.getParameter("user_role");
    UserManager user = null;
    if(currentRole.equals("admin")||currentRole.equals("user")) {
        user = new AdminManager();
    } else if(currentRole.equals("teacher")){
        user = new TeacherManager();
    } else {
        user = new StudentManager();
    }
%>

<html>
<head>
    <title>Користувач</title>
</head>
<body>
<div>
    <%  Object status = request.getParameter("status");
        if(status!=null){
            if(status.toString().equals("false")){%>
    <h3>Щось пішло не так, перевірте коректність введених даних....</h3>
    <%}else{%>
    <h3>Операція виконана успішно....</h3>
    <%}
    }%>

    <div><a href="<%=request.getContextPath()%>\admin\users.jsp">Повернутися до списку</a></div>
    <form action="<%=request.getContextPath()%>/admin/changeuser" method="post">
        <div>Ім'я: <input type="text" name="username" value="" /></div>
        <div>Id: <%=user.getId()%></div>
        <div>Права:<input type="text" name="user_role" value="<%=currentRole%>" readonly/></div>
        Логін: <input type="text" name="userlogin" value="" /><br>
        Пароль: <input type="text" name="userpassword" value="" /><br>
        <%if(currentRole.equals("teacher")) {%>
        <h3>Предмети:</h3>
        <table>
            <tr>
                <td> </td>
                <% ArrayList<Integer> classes = myManager.getClasses();
                    ArrayList<Integer> lessons = myManager.getLessons();
                    for(int i = 0; i < classes.size(); i++){%>
                <td><%=DataManager.getClassById(classes.get(i))%></td>
                <%}%>
            </tr>
            <%for(int i = 0; i < lessons.size(); i++){%>
            <tr>
                <td><%=DataManager.getLessonById(lessons.get(i))%></td>
                <%for(int j = 0; j < classes.size(); j++){%>
                <td>
                    <input type="checkbox" name="class<%=classes.get(j)%><%=lessons.get(i)%>" value="<%=lessons.get(i)%>"/>
                </td>
                <%}%>
            </tr>
            <%}%>
        </table>
        </li>
        <%} else if(currentRole.equals("student")){%>
        Клас: <input name="userclass" list="classList" value=""/>
        <datalist id="classList">
            <%  ArrayList<Integer> classes = myManager.getClasses();
                for(int i = 0; i < classes.size(); i++){%>
            <option value="<%=classes.get(i)%>"><%=DataManager.getClassById(classes.get(i))%></option>
            <%}%>
        </datalist>
        <%}%>
        <%if(!currentRole.equals("user")){%><br>
        <button type="submit" name="what" value="add">Додати</button>
        <%}%>
    </form>
</div>
</body>
</html>