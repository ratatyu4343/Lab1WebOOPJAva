package Controllers;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class HomeController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        switch (req.getSession().getAttribute("Role").toString()){
            case "student":
                req.getRequestDispatcher("/student/panel.jsp").forward(req, resp);
                break;
            case "teacher":
                req.getRequestDispatcher("/teacher/panel.jsp").forward(req, resp);
                break;
            case "admin":
                req.getRequestDispatcher("/admin/panel.jsp").forward(req, resp);
                break;
        }
    }
}
