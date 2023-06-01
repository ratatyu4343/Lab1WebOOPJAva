package Controllers;

import Menegers.Tests.QuestionManager;
import Menegers.Tests.TestManager;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class QuestionController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String type = req.getParameter("what");
        int testId = Integer.parseInt(req.getParameter("test_id"));
        TestManager test = new TestManager();
        test.getTestById(testId);
        if(type.equals("add1")) {
            test.addQuestion(false, "");
        } else if(type.equals("add2")) {
            test.addQuestion(true, "");
        } else if(type.equals("delete")){
            int questId = Integer.parseInt(req.getParameter("question_id"));
            test.deleteQuestion(questId);
        } else if(type.equals("addask")) {
            int questId = Integer.parseInt(req.getParameter("question_id"));
            QuestionManager manager = new QuestionManager();
            manager.getQuestionById(questId);
            manager.addAns(false, "");
        } else if(type.equals("update")){
            String ans = req.getParameter("a");
        }
        resp.sendRedirect(req.getHeader("Referer"));
    }
}
