package Menegers.Tests;

import Menegers.DataBase.DataManager;
import lombok.Getter;
import lombok.Setter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class QuestionManager {
    @Getter
    @Setter
    private int id = -1;
    @Getter
    @Setter
    private boolean type;
    @Getter
    @Setter
    private String name;
    @Getter
    @Setter
    private ArrayList<AnswerManager> answers;
    @Getter
    @Setter
    private int test_id = -1;
    public QuestionManager() {
        answers = new ArrayList<>();
    }

    public void getQuestionById(int ID) {
        try {
            Connection connection = DataManager.getConnection();
            PreparedStatement statement = connection.prepareStatement("SELECT * from questions where \"questionId\"=?");
            statement.setInt(1, ID);
            ResultSet result = statement.executeQuery();
            if(result != null && result.next())  {
              setId(ID);
              setName(result.getString("name"));
              setType(result.getBoolean("type"));
              setTest_id(result.getInt("testId"));
            }
            loadAnswers();
        } catch (Exception e) {
            DataManager.logger.error(e.getMessage());
        }
    }

    public void loadAnswers(){
        answers.clear();
        try {
            Connection connection = DataManager.getConnection();
            PreparedStatement statement = connection.prepareStatement("SELECT answerid from answers WHERE questionid=?;");
            statement.setInt(1, getId());
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                AnswerManager answer = new AnswerManager();
                answer.getAnswerById(result.getInt("answerid"));
                answers.add(answer);
            }
            connection.close();
        }catch (Exception e) {
            DataManager.logger.error(e.getMessage());
        }
    }

    public void addAns(boolean isRight, String nam) {
        try {
            Connection connection = DataManager.getConnection();
            PreparedStatement statement = connection.prepareStatement(
                    "insert into answers (questionid, name, \"right\") VALUES (?, ?, ?);"
            );
            statement.setInt(1, getId());
            statement.setString(2, nam);
            statement.setBoolean(3, isRight);
            statement.execute();
        } catch (Exception e) {
            DataManager.logger.error(e.getMessage());
        }
    }

}
