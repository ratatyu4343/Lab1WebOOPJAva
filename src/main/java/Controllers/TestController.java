package Controllers;

import Menegers.Tests.AnswerManager;
import Menegers.Tests.QuestionManager;
import Menegers.Tests.TestManager;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class TestController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/teacher/testform.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse resp) throws ServletException, IOException {
        String s = request.getParameter("what").toString();
        Integer iDD = Integer.parseInt(request.getParameter("test_id"));
        TestManager testManager = new TestManager();
        testManager.getTestById(iDD);
        if(s.equals("update")) {
            String testName = request.getParameter("testname");
            String testDescription = request.getParameter("testdiscription");
            int testLessonId = Integer.parseInt(request.getParameter("testlesson"));

            // Update test properties
            testManager.setName(testName);
            testManager.setDiscription(testDescription);
            testManager.setLessonId(testLessonId);

            // Update questions
            int questionCount = testManager.getQuestions().size();
            for (int i = 1; i <= questionCount; i++) {
                String questionName = request.getParameter("q" + i);
                QuestionManager question = testManager.getQuestions().get(i - 1);

                // Update question name
                question.setName(questionName);

                // Update answers
                int answerCount = question.getAnswers().size();
                for (int j = 1; j <= answerCount; j++) {
                    String answerName = request.getParameter("ansname" + j);
                    boolean isAnswerRight = request.getParameter("ansright" + j) != null;
                    AnswerManager answer = question.getAnswers().get(j - 1);

                    // Update answer properties
                    answer.setName(answerName);
                    answer.setRight(isAnswerRight);
                }
            }
        }

        resp.sendRedirect(request.getHeader("Referer"));
    }
}
