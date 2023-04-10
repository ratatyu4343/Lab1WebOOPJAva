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
    int currentUserId = Integer.parseInt(request.getParameter("user_id"));
    String currentRole = request.getParameter("user_role");
    UserManager user = null;
    if(currentRole.equals("admin")||currentRole.equals("user")) {
        user = new AdminManager();
        ((AdminManager)user).getUserById(currentUserId);
    } else if(currentRole.equals("teacher")){
        user = new TeacherManager();
        ((TeacherManager)user).getTeacherById(currentUserId);
    } else {
        user = new StudentManager();
        ((StudentManager)user).getStudentById(currentUserId);
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
        <div>Ім'я: <input type="text" name="username" value="<%=user.getName()%>" /></div>
        <div>Id: <%=user.getId()%></div>
        <div>Права:<input type="text" name="user_role" value="<%=currentRole%>" readonly/></div>
        Логін: <input type="text" name="userlogin" value="<%=user.getUserLogin()%>" /><br>
        Пароль: <input type="text" name="userpassword" value="<%=user.getUserPassword()%>" /><br>
        <%if(currentRole.equals("teacher")) {%>
                <h3>Предмети:</h3>
                <table>
                    <tr>
                        <td> </td>
                     <% ArrayList<Integer> classes = myManager.getClasses();
                        ArrayList<Integer> lessons = myManager.getLessons();
                         Map<Integer, ArrayList<Integer>> userLess = ((TeacherManager)user).getLessons();
                        for(int i = 0; i < classes.size(); i++){%>
                            <td><%=DataManager.getClassById(classes.get(i))%></td>
                        <%}%>
                    </tr>
                    <%for(int i = 0; i < lessons.size(); i++){%>
                        <tr>
                            <td><%=DataManager.getLessonById(lessons.get(i))%></td>
                            <%for(int j = 0; j < classes.size(); j++){%>
                                <td>
                                    <input type="checkbox" name="class<%=classes.get(j)%><%=lessons.get(i)%>" value="<%=lessons.get(i)%>"
                                            <%=userLess.get(lessons.get(i))!=null &&
                                                    userLess.get(lessons.get(i)).contains(classes.get(j))?"checked":" "%>/>
                                </td>
                            <%}%>
                        </tr>
                    <%}%>
                </table>
            </li>
        <%} else if(currentRole.equals("student")){%>
            Клас: <input name="userclass" list="classList" value="<%=((StudentManager)user).getClassId()%>"/>
            <datalist id="classList">
                <%  ArrayList<Integer> classes = myManager.getClasses();
                    for(int i = 0; i < classes.size(); i++){%>
                    <option value="<%=classes.get(i)%>"><%=DataManager.getClassById(classes.get(i))%></option>
                    <%}%>
            </datalist>
        <%}%>
        <%if(!currentRole.equals("user")){%><br>
        <button type="submit" name="what" value="update">Оновити</button>
        <button type="submit" name="what" value="delete">Видалити</button>
        <%}%>
    </form>
</div>
</body>
</html>
