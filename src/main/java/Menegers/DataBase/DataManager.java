package Menegers.DataBase;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.simple.SimpleLogger;

import java.sql.*;

public class DataManager {

    public static final String DBPath = "jdbc:postgresql://localhost:5432/TestsLab1";
    public static final String user = "postgres";
    public static final String password = "1234";
    public static final Logger logger = LogManager.getLogger(DataManager.class.getName());

    public static Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(DBPath, user, password);
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return connection;
    }


}
