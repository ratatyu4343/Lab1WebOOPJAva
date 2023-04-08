package Menegers.Tests;

import Menegers.DataBase.DataManager;
import lombok.Getter;
import lombok.Setter;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class AnswerManager {
    @Getter
    @Setter
    private int id = -1;
    @Getter
    @Setter
    private String name;
    @Getter
    @Setter
    private boolean right;
    @Getter
    @Setter
    private int questionid = -1;

    public void getAnswerById(int ID){
        try{
            Connection connection = DataManager.getConnection();
            PreparedStatement statement = connection.prepareStatement("SELECT * from answers WHERE answerid=?");
            statement.setInt(1, ID);
            ResultSet result = statement.executeQuery();
            if(result != null && result.next()) {
                setId(ID);
                setName(result.getString("name"));
                setRight(result.getBoolean("right"));
                setQuestionid(result.getInt("questionid"));
            }
        }
        catch (Exception e) {
            DataManager.logger.error(e.getMessage());
        }
    }

    
}
