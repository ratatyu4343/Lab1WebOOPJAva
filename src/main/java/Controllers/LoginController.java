package Controllers;


import Menegers.DataBase.DataManager;
import Menegers.Users.AdminManager;
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
                break;
            }
            case "teacher": {
                break;
            }
            case "administrator": {
                AdminManager user = new AdminManager();
                if (user.getAdminById(log, pas)) {
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
}
