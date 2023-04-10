package Controllers;


import Menegers.DataBase.DataManager;
import Menegers.Users.AdminManager;
import Menegers.Users.StudentManager;
import Menegers.Users.TeacherManager;
import Menegers.Users.UserManager;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class LoginController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher dispatcher = req.getRequestDispatcher("login.jsp");
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String pas = req.getParameter("password");
        String log = req.getParameter("login");
        String rol = req.getParameter("rolecheck");

        switch (rol) {
            case "student": {
                StudentManager user = new StudentManager();
                if(user.getStudentByLogin(log, pas)){
                    String jwt = DataManager.createJwt(Integer.toString(user.getId()), "teacher", DataManager.EXPIRATION_TIME);
                    req.getSession().setAttribute("Authorization", "Bearer " + jwt);
                    req.getSession().setAttribute("Role", "student");
                    req.getSession().setAttribute("UserId", Integer.toString(user.getId()));
                    resp.sendRedirect(req.getContextPath() + "/");
                } else{
                    resp.sendRedirect(req.getContextPath() + "/login");
                }
                break;
            }
            case "teacher": {
                TeacherManager user = new TeacherManager();
                if(user.getTeacherByLogin(log, pas)) {
                    String jwt = DataManager.createJwt(Integer.toString(user.getId()), "teacher", DataManager.EXPIRATION_TIME);
                    req.getSession().setAttribute("Authorization", "Bearer " + jwt);
                    req.getSession().setAttribute("Role", "teacher");
                    req.getSession().setAttribute("UserId", Integer.toString(user.getId()));
                    resp.sendRedirect(req.getContextPath() + "/");
                } else{
                    resp.sendRedirect(req.getContextPath() + "/login");
                }
                break;
            }
            case "administrator": {
                AdminManager user = new AdminManager();
                if (user.getAdminByLogin(log, pas)) {
                    String jwt = DataManager.createJwt(Integer.toString(user.getId()), "admin", DataManager.EXPIRATION_TIME);
                    req.getSession().setAttribute("Authorization", "Bearer " + jwt);
                    req.getSession().setAttribute("Role", "admin");
                    req.getSession().setAttribute("UserId", Integer.toString(user.getId()));
                    resp.sendRedirect(req.getContextPath() + "/");
                } else {
                    resp.sendRedirect(req.getContextPath() + "/login");
                }
                break;
            }
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getSession().setAttribute("logout", "true");
        resp.sendRedirect(req.getContextPath() + "/login");
    }
}
