package com.hehetenya.test_forms.dao.impl;


import com.hehetenya.test_forms.dao.AnswerDao;
import com.hehetenya.test_forms.dao.ConnectionPool;
import com.hehetenya.test_forms.entity.Answer;
import com.hehetenya.test_forms.entity.Test;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AnswerDaoImpl extends AnswerDao {

    private static final String GET_ALL_ANSWERS = "SELECT * FROM answers";
    private static final String GET_ANSWER_BY_ID = "SELECT * FROM answers WHERE id = ?";
    private static final String UPDATE_ANSWER = "UPDATE answers SET name = ?, is_correct = ?, question_id = ? WHERE id = ?";
    private static final String CREATE_ANSWER = "INSERT INTO answers (name, is_correct, question_id) VALUES (?, ?, ?)";
    private static final String DELETE_ANSWER = "DELETE FROM answers WHERE id = ?";

    @Override
    public List<Answer> getAll() {
        List<Answer> answers = new ArrayList<>();
        try(Connection con = ConnectionPool.getInstance().getConnection();
            Statement stmt = con.createStatement()){
            ResultSet rs = stmt.executeQuery(GET_ALL_ANSWERS);
            while (rs.next()){
                answers.add(new Answer(rs.getInt(1),
                        rs.getString(3),
                        rs.getBoolean(4),
                        rs.getInt(2)));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        //System.out.println(answers);
        return answers;
    }

    @Override
    public Answer getById(int id) {
        List<Answer> answers = getAll();
        for (Answer a:
                answers) {
            if(a.getId() == id){
                return a;
            }
        }
        return null;
    }

    @Override
    public void update(Answer answer) {
        try(Connection con = ConnectionPool.getInstance().getConnection();
            PreparedStatement ps = con.prepareStatement(UPDATE_ANSWER)){
            int k = 0;
            ps.setString(++k, answer.getText());
            ps.setBoolean(++k, answer.isCorrect());
            ps.setInt(++k, answer.getQuestionId());
            ps.setInt(++k, answer.getId());
            if(ps.executeUpdate() == 0){
                throw new SQLException("Update answer failed");
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Answer answer) {
        try(Connection con = ConnectionPool.getInstance().getConnection();
            PreparedStatement ps = con.prepareStatement(DELETE_ANSWER)){
            ps.setInt(1, answer.getId());
            if(ps.executeUpdate() == 0){
                throw new SQLException("Delete answer failed");
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public void create(Answer answer) {
        try(Connection con = ConnectionPool.getInstance().getConnection();
            PreparedStatement ps = con.prepareStatement(CREATE_ANSWER)){
            int k = 0;
            ps.setString(++k, answer.getText());
            ps.setBoolean(++k, answer.isCorrect());
            ps.setInt(++k, answer.getQuestionId());
            if(ps.executeUpdate() == 0){
                throw new SQLException("Create answer failed");
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void insertAnswer(Connection con, Answer answer, int questionId) throws SQLException {
        try(PreparedStatement ps = con.prepareStatement(CREATE_ANSWER)){
            //System.out.println("inserting answer. text ==>" + answer.getText());
            int k = 0;
            ps.setString(++k, answer.getText());
            ps.setBoolean(++k, answer.isCorrect());
            ps.setInt(++k, questionId);
            if(ps.executeUpdate() == 0){
                throw new SQLException("Cannot create new answer");
            }
        }
    }
}
