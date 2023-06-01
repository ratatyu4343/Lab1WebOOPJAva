<%@ page import="Menegers.Users.AdminManager" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="Menegers.DataBase.DataManager" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
  int userid = Integer.parseInt(session.getAttribute("UserId").toString());
  AdminManager admin = new AdminManager();
  admin.getUserById(userid);
  ArrayList<Integer> classes = admin.getClasses();
%>
<html>
<head>
    <title>Класи</title>
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
</head>
<body>
  <div><a href="<%=request.getContextPath()%>\">Повернутися на головну</a></div>
  <form method="post" action="<%=request.getContextPath()%>\admin\changeclass">
    <input type="text" name="classlitera" placeholder="Назва класу"/>
    <input type="number" min="1" max="12" name="classyear" placeholder="Рік навчання"/>
    <button type="submit" name="what" value="add">Додати</button>
  </form>
  <ul>
    <%if(classes!=null){
      for(int i = 0; i < classes.size(); i++){%>
    <li>
      <form action="<%=request.getContextPath()%>\admin\changeclass?classid=<%=classes.get(i)%>" method="post">
        <%=classes.get(i)+" "+ DataManager.getClassById(classes.get(i))%>
        <button type="submit" name="what" value="delete"> видалити</button>
      </form>
    </li>
    <%}}%>
  </ul>
</body>
</html>
