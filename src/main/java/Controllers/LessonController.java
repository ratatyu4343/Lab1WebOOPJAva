package Controllers;

import Menegers.Users.AdminManager;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LessonController extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        AdminManager manager = new AdminManager();
        String what = req.getParameter("what");
        if(what!=null){
            if(what.equals("add")) {
                manager.addLesson(req.getParameter("lessonname"));
            } else if(what.equals("delete")) {
                manager.deleteLesson(Integer.parseInt(req.getParameter("lessonid")));
            }
        }
        resp.sendRedirect(req.getHeader("Referer"));
    }

}
