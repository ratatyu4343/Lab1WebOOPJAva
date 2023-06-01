<%@ page import="Menegers.Tests.TestManager" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="Menegers.Users.TeacherManager" %>
<%@ page import="java.util.Set" %>
<%@ page import="Menegers.DataBase.DataManager" %>
<%@ page import="Menegers.Tests.QuestionManager" %>
<%@ page import="Menegers.Tests.AnswerManager" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    int test_id = Integer.parseInt(request.getParameter("test_id"));
    TestManager test = new TestManager();
    test.getTestById(test_id);
%>
<html>
<head>
    <title>Тест</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
</head>
<body>
        <div><input type="text" value="<%=test.getName()%>" name="testname"></div>
        <div><textarea type="tex" name="testdiscription"><%=test.getDiscription()%></textarea></div>
        <div><input list="datalist" value="<%=test.getLessonId()%>" name="testlesson"></div>
        <datalist id="datalist">
            <%
                TeacherManager teacher = new TeacherManager();
                teacher.getTeacherById(Integer.parseInt(request.getSession().getAttribute("UserId").toString()));
                Set<Integer> less = teacher.getLessons().keySet();
            for(Integer i : less){%>
                <option value="<%=i%>"><%=DataManager.getLessonById(i)%></option>
            <%}%>
        </datalist>
        <ol>
        <%
            ArrayList<QuestionManager> questions = test.getQuestions();
            int qNum = 1;
            for(QuestionManager question : questions){
        %>
        <div><li><div><input type="text" name="q<%=qNum%>" value="<%=question.getName()%>"></div>
            <ol>
                <form method="post" action="<%=request.getContextPath()%>/teacher/changequestion?test_id=<%=test.getId()%>&question_id=<%=question.getId()%>">
            <%  ArrayList<AnswerManager> answers = question.getAnswers();
                int ansNum = 1;
                for(AnswerManager answer : answers){%>
                    <li>
                        <input type="text" value="<%=answer.getName()%>" name="ansname<%=ansNum%>">
                        <input type="<%=question.isType()?"checkbox":"radio"%>" value="<%=answer.getId()%>" name="ansright<%=ansNum%>" <%=answer.isRight()?"checked":""%>>
                    </li>
                <%ansNum += 1;}%>
                    <button type="submit" value="delete" name="what">Видалити питання</button>
                    <button type="submit" value="addask" name="what">Додати відповідь</button>
                    <button type="submit" value="update" name="what">Оновити</button>
                </form>
            </ol>
        <%}%>
        </li>
        </div>
        </ol>

        <form method="post" action="<%=request.getContextPath()%>/teacher/changequestion?test_id=<%=test.getId()%>">
            <button type="submit" value="add1" name="what">Додати питання з 1 відповідю</button>
            <button type="submit" value="add2" name="what">Додати питання з декількома відповідями</button>
        </form>
</body>
</html>
