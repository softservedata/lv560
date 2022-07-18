package com.hehetenya.test_forms.dao.impl;

import com.hehetenya.test_forms.dao.ConnectionPool;
import com.hehetenya.test_forms.dao.Dao;
import com.hehetenya.test_forms.entity.*;
import com.hehetenya.test_forms.exeptions.DBException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Dao interface implementation for PostgresSQL database using JDBC API
 * that performs CRUD operations on objects of type Question.
 */
public class QuestionDaoImpl implements Dao<Question> {
    private static final String GET_ALL_QUESTIONS = "SELECT * FROM questions";
    private static final String GET_QUESTION_BY_ID = "SELECT * FROM questions WHERE id = ?";
    private static final String UPDATE_QUESTION = "UPDATE questions SET name = ?, points = ? WHERE id = ?";
    private static final String CREATE_QUESTION = "INSERT INTO questions (name, points) VALUES (?, ?)";
    private static final String DELETE_QUESTION = "DELETE FROM questions WHERE id = ?";

    @Override
    public List<Question> getAll() throws DBException {
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
            throw new DBException("Cannot get all questions", e);
        }
        return questions;
    }

    @Override
    public Question getById(int id) throws DBException {
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
            throw new DBException("Cannot get question by id", e);
        }
        return question;
    }

    @Override
    public void update(Question question) throws DBException {
        try(Connection con = ConnectionPool.getInstance().getConnection();
            PreparedStatement ps = con.prepareStatement(UPDATE_QUESTION)){
            int k = 0;
            ps.setString(++k, question.getText());
            ps.setInt(++k, question.getId());
            if(ps.executeUpdate() == 0){
                throw new SQLException("Update question failed");
            }
        }catch (SQLException e){
            throw new DBException("Cannot update question", e);
        }
    }

    @Override
    public void delete(Question question) throws DBException {
        try(Connection con = ConnectionPool.getInstance().getConnection();
            PreparedStatement ps = con.prepareStatement(DELETE_QUESTION)){
            ps.setInt(1, question.getId());
            if(ps.executeUpdate() == 0){
                throw new SQLException("Delete question failed");
            }
        }catch (SQLException e){
            throw new DBException("Cannot delete question", e);
        }
    }

    @Override
    public void create(Question question) throws DBException {
        try(Connection con = ConnectionPool.getInstance().getConnection();
            PreparedStatement ps = con.prepareStatement(CREATE_QUESTION)){
            int k = 0;
            ps.setString(++k, question.getText());
            ps.setInt(++k, question.getPoints());
            if(ps.executeUpdate() == 0){
                throw new SQLException("Create question failed");
            }
        }catch (SQLException e){
            throw new DBException("Cannot create question", e);
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
            for (Option a: question.getAnswers()) {
                DaoFactory.getAnswerDao().insertAnswer(con, a, questionId);
            }
        }
        return questionId;
    }

}
