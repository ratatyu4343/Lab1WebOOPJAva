package Menegers.Users;

import Menegers.DataBase.DataManager;
import lombok.Getter;
import lombok.Setter;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class StudentManager extends UserManager {
    @Getter
    @Setter
    private int classId;

    public boolean getStudentByLogin(String log, String pas){
        if(getUserByLogin(log, pas)){
            try{
                Connection connection = DataManager.getConnection();
                PreparedStatement statement = connection.prepareStatement("SELECT * from students WHERE \"userId\"=?;");
                statement.setInt(1, getId());
                ResultSet result = statement.executeQuery();
                if((result != null) && result.next()) {
                    setClassId(result.getInt("classId"));
                    return true;
                }
                else
                    return false;
            } catch (Exception e) {
                DataManager.logger.error(e.getMessage());
                return false;
            }
        } else {
            return false;
        }
    }
    public boolean getStudentById(int ID){
        if(getUserById(ID)) {
            try {
                Connection connection = DataManager.getConnection();
                PreparedStatement statement = connection.prepareStatement("SELECT * from students WHERE \"userId\"=?;");
                statement.setInt(1, getId());
                ResultSet result = statement.executeQuery();
                if((result != null) && result.next()) {
                    setClassId(result.getInt("classId"));
                    return true;
                }
                else
                    return false;
            } catch (Exception e) {
                DataManager.logger.error(e.getMessage());
                return false;
            }
        }
        else return false;
    }
    public boolean addStudent(String log, String pas, String nam, int IdOfClass){
        if(addUser(log, pas, nam)){
            StudentManager student = new StudentManager();
            if(!student.getStudentByLogin(log, pas)){
                UserManager user = new UserManager();
                user.getUserByLogin(log, pas);
                try {
                    Connection connection = DataManager.getConnection();
                    PreparedStatement statement = connection.prepareStatement(
                            "insert into students (\"userId\",\"classId\") values (?, ?);"
                    );
                    DataManager.logger.error("|||||"+user.getId()+"|||||");
                    statement.setInt(1, user.getId());
                    statement.setInt(2, IdOfClass);
                    statement.execute();
                    return true;
                } catch (Exception e) {
                    DataManager.logger.error("32r33r3r"+e.getMessage());
                    return false;
                }
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

}
