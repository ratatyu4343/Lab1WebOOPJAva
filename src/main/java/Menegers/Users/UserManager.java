package Menegers.Users;


import Menegers.DataBase.DataManager;
import lombok.Getter;
import lombok.Setter;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UserManager {
    @Getter
    @Setter
    protected int id = -1;
    @Getter
    @Setter
    protected String name = "";
    public boolean getUserByLogin(String login, String password) {
        try {
            Connection connection = DataManager.getConnection();
            PreparedStatement statement = connection.prepareStatement("SELECT * from users WHERE login=? and pasword=?");
            statement.setString(1, login);
            statement.setString(2, password);
            ResultSet result = statement.executeQuery();
            if(result != null && result.next()) {
                setId(result.getInt("userId"));
                setName(result.getString("name"));
                connection.close();
                return true;
            } else {
                connection.close();
                return false;
            }
        } catch (Exception e) {
            DataManager.logger.error(e.getMessage());
            return false;
        }
    }
    public boolean getUserById(int id) {
        try {
            Connection connection = DataManager.getConnection();
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM users WHERE \"userId\"=?;");
            statement.setInt(1, id);
            ResultSet result = statement.executeQuery();
            if (result != null && result.next()) {
                setId(result.getInt("userId"));
                setName(result.getString("name"));
                connection.close();
                return true;
            }
            connection.close();
            return false;
        } catch (Exception e) {
            DataManager.logger.error(e.getMessage());
            return false;
        }
    }


}
