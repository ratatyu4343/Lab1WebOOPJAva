package Menegers.Tests;

import Menegers.DataBase.DataManager;
import lombok.Getter;
import lombok.Setter;

import java.sql.*;
import java.util.ArrayList;

public class TestManager {
    @Getter
    @Setter
    private int id = -1;
    @Getter
    @Setter
    private ArrayList<QuestionManager> questions;
    @Getter
    @Setter
    private String name;
    @Getter
    @Setter
    private String discription;

    public TestManager(){
        questions = new ArrayList<>();
    }
    public boolean getTestById(int ID) {
        try {
            Connection connection = DataManager.getConnection();
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT name, description FROM tests WHERE \"testId\" =?");
            statement.setInt(1, ID);
            ResultSet result = statement.executeQuery();
            if(result != null && result.next()) {
                setId(ID);
                setName(result.getString("name"));
                setDiscription(result.getString("description"));
                connection.close();
                loadQuestions();
                return true;
            } else {
                connection.close();
                return false;
            }
        }catch (Exception e) {
            DataManager.logger.error(e.getMessage());
        }
        return false;
    }
    public void loadQuestions() {
        try{
            questions.clear();
            Connection connection = DataManager.getConnection();
            PreparedStatement statement = connection.prepareStatement("SELECT \"questionId\" FROM questions WHERE \"testId\" = ?;");
            statement.setInt(1, getId());
            ResultSet result = statement.executeQuery();
            while(result.next()) {
                QuestionManager question = new QuestionManager();
                question.getQuestionById(result.getInt("questionId"));
                questions.add(question);
            }
            connection.close();
        } catch (Exception e) {
            DataManager.logger.error(e.getMessage());
        }
    }
}
