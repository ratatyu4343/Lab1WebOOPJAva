package Controllers;

import Menegers.Users.AdminManager;
import Menegers.Users.StudentManager;
import Menegers.Users.TeacherManager;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class UserController extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String what = req.getParameter("what");
        String user_type = req.getParameter("user_role");
        String status = "true";
        String log = req.getParameter("userlogin");
        String pas = req.getParameter("userpassword");
        String nam = req.getParameter("username");
        String us_id = req.getParameter("user_id");
        if(what.equals("add")){
            if(user_type.equals("student")){
                int clasId = Integer.parseInt(req.getParameter("userclass"));
                StudentManager manager = new StudentManager();
                if(!manager.addStudent(log, pas, nam, clasId)) status = "false";
            } else if(user_type.equals("teacher")){
                AdminManager manager = new AdminManager();
                TeacherManager teacher = new TeacherManager();
                Map<Integer, ArrayList<Integer>> less = new HashMap<>();
                for(int g = 0; g < manager.getClasses().size(); g++){
                    for(int i = 0; i < manager.getLessons().size(); i++){
                        if(req.getParameter("class"+manager.getClasses().get(g)+
                                Integer.toString(manager.getLessons().get(i)))!=null){
                            if(less.get(manager.getLessons().get(i)) == null)
                                less.put(manager.getLessons().get(i), new ArrayList<>());
                            less.get(manager.getLessons().get(i)).add(manager.getClasses().get(g));
                        }
                    }
                }
                if(!teacher.addTeacher(log, pas, nam, less)) status="false";
                /*System.out.println(manager.getClasses());
                System.out.println(manager.getLessons());
                resp.setContentType("text/html");
                PrintWriter out = resp.getWriter();
                // get all parameter names
                Set<String> paramNames = req.getParameterMap().keySet();
                // iterating over parameter names and get its value
                for (String name : paramNames) {
                    String value = req.getParameter(name);
                    out.write(name + ": " + value);
                    out.write("<br>");
                }return;*/
            }
        } else if(what.equals("update")){
            if(user_type.equals("student")){
                int clasId = Integer.parseInt(req.getParameter("userclass"));
                StudentManager manager = new StudentManager();
                manager.getUserById(Integer.parseInt(us_id));

            } else if(user_type.equals("teacher")){

            }
        } else if(what.equals("delete")){
            return;
        }
        resp.sendRedirect(req.getHeader("Referer").replace(req.getParameter("status")!=null?(
                req.getParameter("status").equals("true")?"&status=true":"&status=false"):"",
                "")+"&status="+status);
    }
}
