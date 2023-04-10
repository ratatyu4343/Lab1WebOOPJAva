package Controllers;

import Menegers.Users.AdminManager;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ClassController extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String what = req.getParameter("what");
        AdminManager manager = new AdminManager();
        if(what.equals("add")){
            int year = Integer.parseInt(req.getParameter("classyear"));
            String litera = req.getParameter("classlitera");
            manager.addClass(year, litera);
        }
        else if(what.equals("delete")){
            int id = Integer.parseInt(req.getParameter("classid"));
            manager.deleteClass(id);
        }
        resp.sendRedirect(req.getHeader("Referer"));
    }

}
