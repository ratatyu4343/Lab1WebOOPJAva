<%@ page import="java.util.ArrayList" %>
<%@ page import="Menegers.Users.UserManager" %>
<%@ page import="Menegers.Users.AdminManager" %>
<%@ page import="Menegers.Users.TeacherManager" %>
<%@ page import="Menegers.Users.StudentManager" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    AdminManager admin = new AdminManager();
    admin.getUserById(Integer.parseInt(session.getAttribute("UserId").toString()));
    Object categorie = request.getParameter("categorie");
    String categorieStr = "";
    if(categorie != null)
        categorieStr = categorie.toString();
%>
<html>
<head>
    <title>Користувачі</title>
</head>
<body>
    <div><a href="<%=request.getContextPath()%>\">Повернутися на головну</a></div>
    <form action="users.jsp" method="post">
        <input type="radio" name="categorie" value="all" id="allUsers" <%=categorieStr.equals("all")?"checked":""%>/>
        <label for="allUsers">Усі учасники</label>
        <input type="radio" name="categorie" value="teachers" id="teachUsers" <%=categorieStr.equals("teachers")?"checked":""%>/>
        <label for="teachUsers">Учителі</label>
        <input type="radio" name="categorie" value="students" id="studUsers" <%=categorieStr.equals("students")?"checked":""%>/>
        <label for="studUsers">Учні  </label>
        <button type="submit">Відфільтрувати</button>
    </form>
    <form action="<%=request.getContextPath()%>\admin\newuser.jsp" method="post">
        <button type="submit" name="user_role" value="student">Додати учня</button>
        <button type="submit" name="user_role" value="teacher">Додати учителя</button>
    </form>
    <table>
    <%if(categorie == null || categorieStr.equals("all")){
        ArrayList<UserManager> users = admin.getUsers();
        for(int i = 0; i < users.size(); i++){%>
            <tr>
                <td><%=users.get(i).getId()%></td>
                <td>
                    <a href="user.jsp?user_id=<%=users.get(i).getId()%>&user_role=user">
                        <%=users.get(i).getName()%>
                    </a>
                </td>
            </tr>
    <%}}
    else if(categorieStr.equals("teachers")){
        ArrayList<TeacherManager> users = admin.getTeachers();
        if(users != null)
        for(int i = 0; i < users.size(); i++){%>
        <tr>
            <td><%=users.get(i).getId()%></td>
            <td>
                <a href="user.jsp?user_id=<%=users.get(i).getId()%>&user_role=teacher">
                    <%=users.get(i).getName()%>
                </a>
            </td>
        </tr>
    <%}}
    else
    {
        ArrayList<StudentManager> users = admin.getStudents();
        if(users != null)
        for(int i = 0; i < users.size(); i++){%>
        <tr>
            <td><%=users.get(i).getId()%></td>
            <td>
                <a href="user.jsp?user_id=<%=users.get(i).getId()%>&user_role=student">
                    <%=users.get(i).getName()%>
                </a>
            </td>
        </tr>
    <%}}%>
    </table>
</body>
</html>
