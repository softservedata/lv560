package com.hehetenya.test_forms.dao.impl;


import com.hehetenya.test_forms.dao.ConnectionPool;
import com.hehetenya.test_forms.dao.Dao;
import com.hehetenya.test_forms.entity.Option;
import com.hehetenya.test_forms.exeptions.DBException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Dao interface implementation for PostgresSQL database using JDBC API
 * that performs CRUD operations on objects of type Option.
 */
public class OptionDaoImpl implements Dao<Option> {

    private static final String GET_ALL_ANSWERS = "SELECT * FROM answer_options";
    private static final String GET_ANSWER_BY_ID = "SELECT * FROM answer_options WHERE id = ?";
    private static final String UPDATE_ANSWER = "UPDATE answer_options SET name = ?, is_correct = ?, question_id = ? WHERE id = ?";
    private static final String CREATE_ANSWER = "INSERT INTO answer_options (name, is_correct, question_id) VALUES (?, ?, ?)";
    private static final String DELETE_ANSWER = "DELETE FROM answer_options WHERE id = ?";

    @Override
    public List<Option> getAll() throws DBException {
        List<Option> options = new ArrayList<>();
        try(Connection con = ConnectionPool.getInstance().getConnection();
            Statement stmt = con.createStatement()){
            ResultSet rs = stmt.executeQuery(GET_ALL_ANSWERS);
            while (rs.next()){
                options.add(new Option(rs.getInt(1),
                        rs.getString(3),
                        rs.getBoolean(4),
                        rs.getInt(2)));
            }
        }catch (SQLException e){
            throw new DBException("Cannot get all answers", e);
        }
        return options;
    }

    @Override
    public Option getById(int id) throws DBException {
        List<Option> options = getAll();
        for (Option a:
                options) {
            if(a.getId() == id){
                return a;
            }
        }
        return null;
    }

    @Override
    public void update(Option option) throws DBException {
        try(Connection con = ConnectionPool.getInstance().getConnection();
            PreparedStatement ps = con.prepareStatement(UPDATE_ANSWER)){
            int k = 0;
            ps.setString(++k, option.getText());
            ps.setBoolean(++k, option.isCorrect());
            ps.setInt(++k, option.getQuestionId());
            ps.setInt(++k, option.getId());
            if(ps.executeUpdate() == 0){
                throw new SQLException("Update answer failed");
            }
        }catch (SQLException e){
            throw new DBException("Cannot update answer", e);
        }
    }

    @Override
    public void delete(Option option) throws DBException {
        try(Connection con = ConnectionPool.getInstance().getConnection();
            PreparedStatement ps = con.prepareStatement(DELETE_ANSWER)){
            ps.setInt(1, option.getId());
            if(ps.executeUpdate() == 0){
                throw new SQLException("Delete answer failed");
            }
        }catch (SQLException e){
            throw new DBException("Cannot delete answer", e);
        }
    }

    @Override
    public void create(Option option) throws DBException {
        try(Connection con = ConnectionPool.getInstance().getConnection();
            PreparedStatement ps = con.prepareStatement(CREATE_ANSWER)){
            int k = 0;
            ps.setString(++k, option.getText());
            ps.setBoolean(++k, option.isCorrect());
            ps.setInt(++k, option.getQuestionId());
            if(ps.executeUpdate() == 0){
                throw new SQLException("Create answer failed");
            }
        }catch (SQLException e){
            throw new DBException("Cannot create answer", e);
        }
    }

    public void insertAnswer(Connection con, Option option, int questionId) throws SQLException {
        try(PreparedStatement ps = con.prepareStatement(CREATE_ANSWER)){
            int k = 0;
            ps.setString(++k, option.getText());
            ps.setBoolean(++k, option.isCorrect());
            ps.setInt(++k, questionId);
            if(ps.executeUpdate() == 0){
                throw new SQLException("Cannot create new answer");
            }
        }
    }
}
