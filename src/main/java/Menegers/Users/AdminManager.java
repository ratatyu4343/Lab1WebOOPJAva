package Menegers.Users;

import Menegers.DataBase.DataManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class AdminManager extends UserManager {
    public boolean getAdminByLogin(String log, String pas) {
        if(getUserByLogin(log, pas)) {
            try {
                Connection connection = DataManager.getConnection();
                PreparedStatement statement = connection.prepareStatement("SELECT * from admins WHERE \"userId\"=?;");
                statement.setInt(1, getId());
                ResultSet result = statement.executeQuery();
                if((result != null) && result.next())
                    return true;
                else
                    return false;
            } catch (Exception e) {
                DataManager.logger.error(e.getMessage());
                return false;
            }
        }
        else {
            return false;
        }
    }
    public ArrayList<UserManager> getUsers(){
        ArrayList<UserManager> array = null;
        try {
            Connection connection = DataManager.getConnection();
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery("SELECT * from users");
            array = new ArrayList<>();
            while (result.next()){
                UserManager user = new UserManager();
                user.getUserById(result.getInt("userId"));
                array.add(user);
            }
            connection.close();
        } catch (Exception e){
            DataManager.logger.error(e.getMessage());
        }
        return array;
    }
    public ArrayList<TeacherManager> getTeachers(){
        ArrayList<TeacherManager> array = null;
        try {
            Connection connection = DataManager.getConnection();
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(
                    "SELECT * from " +
                         "users inner join teachers " +
                         "ON users.\"userId\"=teachers.\"userId\"");
            array = new ArrayList<>();
            while (result.next()){
                TeacherManager user = new TeacherManager();
                user.getTeacherById(result.getInt("userId"));
                boolean flag = true;
                for(int i = 0; i < array.size(); i++){
                    if(array.get(i).getId() == user.getId()) {
                        flag = false;
                        break;
                    }
                }
                if(flag)
                    array.add(user);
            }
            connection.close();
        } catch (Exception e){
            DataManager.logger.error(e.getMessage());
        }
        return array;
    }
    public  ArrayList<StudentManager> getStudents(){
        ArrayList<StudentManager> array = null;
        try {
            Connection connection = DataManager.getConnection();
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(
                    "SELECT * from " +
                            "users inner join students " +
                            "ON users.\"userId\"=students.\"userId\"");
            array = new ArrayList<>();
            while (result.next()){
                StudentManager user = new StudentManager();
                user.getStudentById(result.getInt("userId"));
                array.add(user);
            }
            connection.close();
        } catch (Exception e){
            DataManager.logger.error(e.getMessage());
        }
        return array;
    }
    public void addLesson(String name){
        try{
            Connection connection = DataManager.getConnection();
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT * from lesson where name=?;"
            );
            statement.setString(1, name);
            ResultSet resultSet = statement.executeQuery();
            if(resultSet == null || !resultSet.next()){
                statement = connection.prepareStatement("INSERT INTO lesson (name) VALUES (?)");
                statement.setString(1, name);
                statement.executeQuery();
            }
        } catch (Exception e) {
            DataManager.logger.error(e.getMessage());
        }
    }
    public ArrayList<Integer> getLessons(){
        ArrayList<Integer> arr = null;
        try{
            Connection connection = DataManager.getConnection();
            Statement statement = connection.createStatement();
            ResultSet set = statement.executeQuery("SELECT * from lesson");
            arr = new ArrayList<>();
            while(set!=null && set.next()){
                arr.add(set.getInt("lessonId"));
            }
        } catch (Exception e){
            DataManager.logger.error(e.getMessage());
        }
        return arr;
    }
    public void deleteLesson(int ID) {
        try{
            Connection connection = DataManager.getConnection();
            PreparedStatement statement = connection.prepareStatement("delete from lesson where \"lessonId\"=?;");
            statement.setInt(1, ID);
            statement.executeQuery();
        } catch (Exception e){
            DataManager.logger.error(e.getMessage());
        }
    }
    public ArrayList<Integer> getClasses(){
        ArrayList<Integer> arr = null;
        try{
            Connection connection = DataManager.getConnection();
            Statement statement = connection.createStatement();
            ResultSet set = statement.executeQuery("SELECT * from classes");
            arr = new ArrayList<>();
            while(set!=null && set.next()){
                arr.add(set.getInt("classId"));
            }
        } catch (Exception e){
            DataManager.logger.error(e.getMessage());
        }
        return arr;
    }
    public void addClass(int year, String litera){
        try{
            Connection connection = DataManager.getConnection();
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT * from classes where litera=? and year=?;"
            );
            statement.setString(1, litera);
            statement.setInt(2, year);
            ResultSet resultSet = statement.executeQuery();
            if(resultSet == null || !resultSet.next()){
                statement = connection.prepareStatement("INSERT INTO classes (litera, year) VALUES (?,?)");
                statement.setString(1, litera);
                statement.setInt(2, year);
                statement.executeQuery();
            }
        } catch (Exception e) {
            DataManager.logger.error(e.getMessage());
        }
    }
    public void deleteClass(int ID){
        try{
            Connection connection = DataManager.getConnection();
            PreparedStatement statement = connection.prepareStatement("delete from classes where \"classId\"=?;");
            statement.setInt(1, ID);
            statement.executeQuery();
        } catch (Exception e){
            DataManager.logger.error(e.getMessage());
        }
    }

}
