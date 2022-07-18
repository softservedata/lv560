package com.hehetenya.test_forms.dao.impl;

import com.hehetenya.test_forms.dao.ConnectionPool;
import com.hehetenya.test_forms.dao.Dao;
import com.hehetenya.test_forms.entity.Option;
import com.hehetenya.test_forms.entity.Result;
import com.hehetenya.test_forms.exeptions.DBException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Dao interface implementation for PostgresSQL database using JDBC API
 * that performs CRUD operations on objects of type Result.
 */
public class ResultDaoImpl implements Dao<Result> {

    private static final String GET_ALL_RESULTS = "SELECT * FROM results;";
    private static final String CREATE_RESULT = "INSERT INTO results (user_id, test_id, grade) VALUES (?, ?, ?);";
    private static final String CREATE_ANSWERS = "INSERT INTO answers (result_id, answer_option_id) VALUES (?, ?);";
    private static final String GET_ANSWERS = "SELECT * FROM answers WHERE result_id = ?;";

    @Override
    public List<Result> getAll() throws DBException {
        List<Result> results = new ArrayList<>();
        try(Connection con = ConnectionPool.getInstance().getConnection();
            Statement stmt = con.createStatement()){
            ResultSet rs = stmt.executeQuery(GET_ALL_RESULTS);
            while (rs.next()){
                results.add(new Result(rs.getInt(1),
                        DaoFactory.getUserDao().getById(rs.getInt(2)),
                        DaoFactory.getTestDao().getById(rs.getInt(3)),
                        rs.getInt(4),
                        getAnswers(rs.getInt(1))));
            }
        }catch (SQLException e){
            throw new DBException("Cannot get all results", e);
        }
        return results;
    }

    private static List<Option> getAnswers(int resultId) throws DBException {
        List<Option> options = new ArrayList<>();
        try(Connection con = ConnectionPool.getInstance().getConnection();
            PreparedStatement ps = con.prepareStatement(GET_ANSWERS)){
            ps.setInt(1, resultId);
            try(ResultSet rs = ps.executeQuery()){
                while(rs.next()){
                    options.add(DaoFactory.getAnswerDao().getById(rs.getInt(2)));
                }
            }
        }catch (SQLException e){
            throw new DBException("Cannot get answers", e);
        }
        return options;
    }

    @Override
    public Result getById(int id) {
        return null;
    }

    @Override
    public void update(Result result) {

    }

    @Override
    public void delete(Result result) {

    }

    @Override
    public void create(Result result) throws DBException {
        Connection con = null;
        try{
            con = ConnectionPool.getInstance().getConnection();
            con.setAutoCommit(false);
            int resultId = createNewResult(con, result);
            for(Option o: result.getAnswers()){
                insertIntoAnswers(con, resultId, o.getId());
            }
        }catch (SQLException e){
            if(con != null) ConnectionPool.rollback(con);
            throw new DBException("Cannot create result", e);
        }finally {
            ConnectionPool.close(con);
        }
    }

    private void insertIntoAnswers(Connection con, int resultId, int id) throws SQLException {
        try(PreparedStatement ps = con.prepareStatement(CREATE_ANSWERS)){
            int k = 0;
            ps.setInt(++k, resultId);
            ps.setInt(++k, id);
            if(ps.executeUpdate() ==  0){
                throw new SQLException("Cannot insert into answers");
            }
            con.commit();
        }
    }

    private int createNewResult(Connection con, Result result) throws SQLException {
        try(PreparedStatement ps = con.prepareStatement
                (CREATE_RESULT, Statement.RETURN_GENERATED_KEYS)){
            int k = 0;
            ps.setInt(++k, result.getUser().getId());
            ps.setInt(++k, result.getTest().getId());
            ps.setInt(++k, result.getGrade());
            if(ps.executeUpdate() == 0){
                throw new SQLException("Cannot create new result");
            }
            try(ResultSet rs = ps.getGeneratedKeys()){
                if(rs.next()){
                    con.commit();
                    return rs.getInt(1);
                }else {
                    throw new SQLException("Result insert failed");
                }
            }
        }
    }
}
