package Menegers.Users;

import Controllers.UserController;
import Menegers.DataBase.DataManager;
import Menegers.Tests.TestManager;
import lombok.Getter;
import lombok.Setter;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class TeacherManager extends UserManager {
    @Getter
    @Setter
    private Map<Integer, ArrayList<Integer>> lessons;
    @Getter
    @Setter
    private ArrayList<TestManager> tests;
    public TeacherManager() {
        lessons = new HashMap<>();
        tests = new ArrayList<>();
    }
    public boolean getTeacherByLogin(String log, String pas) {
        if(getUserByLogin(log, pas)){
            try{
                Connection connection = DataManager.getConnection();
                PreparedStatement statement = connection.prepareStatement("SELECT * from teachers where \"userId\"=?;");
                statement.setInt(1, getId());
                ResultSet result = statement.executeQuery();
                if(result != null && result.next()) {
                    lessons.clear();
                    do{
                        Integer lessonId = result.getInt("lessonId");
                        lessons.put(lessonId, new ArrayList<>());
                        PreparedStatement statement2 = connection.prepareStatement(
                                "SELECT * from teachers_classes where \"teacherId\"=? and \"lessonId\"=?;"
                        );
                        statement2.setInt(1, getId());
                        statement2.setInt(2, lessonId);
                        ResultSet result2 = statement2.executeQuery();
                        if(result2 != null) {
                            while (result2.next()){
                                Integer classid = result2.getInt("classId");
                                lessons.get(lessonId).add(classid);
                            }
                        }
                    } while (result.next());
                    connection.close();
                    getMyTests();
                    return true;
                } else {
                    connection.close();
                    return false;
                }
            }catch (Exception e){
                DataManager.logger.error(e.getMessage());
                return false;
            }
        }else{
            return false;
        }
    }
    public boolean getTeacherById(int ID) {
        if(getUserById(ID)){
            try{
                Connection connection = DataManager.getConnection();
                PreparedStatement statement = connection.prepareStatement("SELECT * from teachers where \"userId\"=?;");
                statement.setInt(1, getId());
                ResultSet result = statement.executeQuery();
                if(result != null && result.next()) {
                    lessons.clear();
                    do{
                        Integer lessonId = result.getInt("lessonId");
                        lessons.put(lessonId, new ArrayList<>());
                        PreparedStatement statement2 = connection.prepareStatement(
                                "SELECT * from teachers_classes where \"teacherId\"=? and \"lessonId\"=?;"
                        );
                        statement2.setInt(1, getId());
                        statement2.setInt(2, lessonId);
                        ResultSet result2 = statement2.executeQuery();
                        if(result2 != null) {
                            while (result2.next()){
                                Integer classid = result2.getInt("classId");
                                lessons.get(lessonId).add(classid);
                            }
                        }
                    }while (result.next());
                    connection.close();
                    getMyTests();
                    return true;
                } else {
                    connection.close();
                    return false;
                }
            }catch (Exception e){
                DataManager.logger.error(e.getMessage());
                return false;
            }
        }else{
            return false;
        }
    }
    public void getMyTests(){
        tests.clear();
        try{
            Connection connection = DataManager.getConnection();
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT * from tests WHERE \"teacherId\" = ?;"
            );
            statement.setInt(1, getId());
            ResultSet result = statement.executeQuery();
            if(result != null)
                while (result.next()){
                    TestManager test = new TestManager();
                    test.getTestById(result.getInt("testId"));
                    tests.add(test);
                }
        } catch (Exception e){
            DataManager.logger.error(e.getMessage());
        }
    }
    public boolean addTeacher(String log, String pas, String nam, Map<Integer, ArrayList<Integer>> less){
        if(addUser(log, pas, nam)) {
            UserManager user = new UserManager();
            user.getUserByLogin(log, pas);
            try {
                Object[] arr = less.keySet().toArray();
                for(int i = 0; i < arr.length; i++) {
                    Connection connection = DataManager.getConnection();
                    PreparedStatement statement = connection.prepareStatement(
                            "insert into teachers (\"userId\", \"lessonId\") values (?, ?)"
                    );
                    statement.setInt(1, user.getId());
                    statement.setInt(2, (Integer) arr[i]);
                    statement.execute();
                    DataManager.logger.error(user.getId()+" "+(Integer) arr[i]+"added");
                    for(int g = 0; g < less.get((Integer) arr[i]).size(); g++) {
                        PreparedStatement statement2 = connection.prepareStatement(
                                "insert into teachers_classes (\"teacherId\",\"lessonId\",\"classId\") values (?,?,?);"
                        );
                        statement2.setInt(1,user.getId());
                        statement2.setInt(2,(Integer) arr[i]);
                        statement2.setInt(3,less.get((Integer) arr[i]).get(g));
                        statement2.execute();
                        DataManager.logger.error(user.getId()+" "+(Integer) arr[i]+" "+less.get((Integer) arr[i]).get(g)+"added");
                    }
                }
                return true;
            }catch (Exception e) {
                DataManager.logger.error(e.getMessage());
                return false;
            }
        } else{
            return false;
        }
    }
}
