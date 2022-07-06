package com.hehetenya.test_forms.dao.impl;

import com.hehetenya.test_forms.dao.ConnectionPool;
import com.hehetenya.test_forms.dao.QuestionDao;
import com.hehetenya.test_forms.entity.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class QuestionDaoImpl extends QuestionDao {

    private static final String GET_ALL_QUESTIONS = "SELECT * FROM questions";
    private static final String GET_QUESTION_BY_ID = "SELECT * FROM questions WHERE id = ?";
    private static final String UPDATE_QUESTION = "UPDATE questions SET name = ?, points = ? WHERE id = ?";
    private static final String CREATE_QUESTION = "INSERT INTO questions (name, points) VALUES (?, ?)";
    private static final String DELETE_QUESTION = "DELETE FROM questions WHERE id = ?";

    @Override
    public List<Question> getAll() {
        List<Question> questions = new ArrayList<>();
        try(Connection con = ConnectionPool.getInstance().getConnection();
            Statement stmt = con.createStatement()){
            ResultSet rs = stmt.executeQuery(GET_ALL_QUESTIONS);
            while (rs.next()){
                questions.add(new Question(rs.getInt(1),
                        rs.getString(2),
                        rs.getInt(3)));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        System.out.println(questions);
        return questions;
    }

    @Override
    public Question getById(int id) {
        Question question = null;
        try(Connection con = ConnectionPool.getInstance().getConnection();
            PreparedStatement ps = con.prepareStatement(GET_QUESTION_BY_ID)){
            ps.setInt(1, id);
            try(ResultSet rs = ps.executeQuery()){
                if(rs.next()){
                    question = new Question(rs.getInt(1),
                            rs.getString(2),
                            rs.getInt(3));
                }
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return question;
    }

    @Override
    public void update(Question question) {
        try(Connection con = ConnectionPool.getInstance().getConnection();
            PreparedStatement ps = con.prepareStatement(UPDATE_QUESTION)){
            int k = 0;
            ps.setString(++k, question.getText());
            ps.setInt(++k, question.getId());
            if(ps.executeUpdate() == 0){
                throw new SQLException("Update question failed");
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Question question) {
        try(Connection con = ConnectionPool.getInstance().getConnection();
            PreparedStatement ps = con.prepareStatement(DELETE_QUESTION)){
            ps.setInt(1, question.getId());
            if(ps.executeUpdate() == 0){
                throw new SQLException("Delete question failed");
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public void create(Question question) {
        try(Connection con = ConnectionPool.getInstance().getConnection();
            PreparedStatement ps = con.prepareStatement(CREATE_QUESTION)){
            int k = 0;
            ps.setString(++k, question.getText());
            ps.setInt(++k, question.getPoints());
            if(ps.executeUpdate() == 0){
                throw new SQLException("Create question failed");
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public int insertQuestion(Connection con, Question question) throws SQLException {
        int questionId =0;
        try(PreparedStatement ps = con.prepareStatement(CREATE_QUESTION, Statement.RETURN_GENERATED_KEYS)){
            int k = 0;
            ps.setString(++k, question.getText());
            ps.setInt(++k, question.getPoints());
            if(ps.executeUpdate() == 0){
                throw new SQLException("Cannot create new question");
            }
            try(ResultSet rs = ps.getGeneratedKeys()){
                if(rs.next()){
                    questionId = rs.getInt(1);
                }
            }

            for (Answer a: question.getAnswers()) {
                DaoFactory.getAnswerDao().insertAnswer(con, a, questionId);
            }
        }
        return questionId;
    }

}
