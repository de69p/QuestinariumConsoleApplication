package repository;

import model.Question;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import repository.dao.QuestionRepository;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class QuestionRepositoryTest {
    private static Connection connection;
    private QuestionRepository impl;

    private int testId = 100;
    private String testText = "test question";
    private String testTopic = "Test topic";
    private Question questionToSave = Question.builder()
            .id(testId)
            .text(testText)
            .topic(testTopic)
            .build();

    @BeforeClass
    public static void createConnection() throws SQLException {
        connection = ConnectionSingleton.getConnection();
        connection.close();
    }

    @Before
    public void saveTestEntity() {
        this.impl = new QuestionRepositoryImp(connection);
        this.impl.save(questionToSave);
    }

    @Test
    public void getTest() {
        Question actual = this.impl.get(testId);
        this.impl.delete(testId);
        Assert.assertEquals(questionToSave, actual);
    }

    @Test
    public void saveTest() {
        Question actual = this.impl.get(testId);
        this.impl.delete(testId);
        Assert.assertEquals(questionToSave, actual);
    }

    @Test
    public void updateTest() {
        String updateText = "Update question";
        Question questionToUpdate = Question.builder().id(testId).text(updateText).topic(testTopic).build();
        this.impl.update(questionToUpdate);
        Question actual = this.impl.get(testId);
        this.impl.delete(testId);
        Assert.assertEquals(questionToUpdate, actual);
    }

    @Test
    public void deleteTest() {
        this.impl.delete(testId);
        int actual = this.impl.getByTopic(this.testTopic).size();
        Assert.assertEquals(0, actual);
    }

    @Test
    public void getByTopicTest() {
        List<Question> actual = this.impl.getByTopic(testTopic);
        List<Question> expected = List.of(questionToSave);
        this.impl.delete(testId);
        Assert.assertEquals(expected, actual);
    }

}