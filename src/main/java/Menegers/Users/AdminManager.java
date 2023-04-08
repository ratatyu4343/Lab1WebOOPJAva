package Menegers.Users;

import Menegers.DataBase.DataManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class AdminManager extends UserManager {
    public boolean getAdminById(String log, String pas) {
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
}
