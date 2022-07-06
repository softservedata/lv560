package com.hehetenya.test_forms.dao.impl;

import com.hehetenya.test_forms.dao.ConnectionPool;
import com.hehetenya.test_forms.dao.ResultDao;
import com.hehetenya.test_forms.entity.Answer;
import com.hehetenya.test_forms.entity.Result;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ResultDaoImpl extends ResultDao {

    private static final String GET_ALL_RESULTS = "SELECT * FROM results";
    private static final String GET_RESULT_BY_ID = "SELECT * FROM results WHERE id = ?";
    private static final String UPDATE_RESULT = "UPDATE results SET name = ?, duration_min = ?, creator_id = ? WHERE id = ?";
    private static final String CREATE_RESULT = "INSERT INTO results (user_id, test_id, grade) VALUES (?, ?, ?)";
    private static final String CREATE_ANSWERSHEET = "INSERT INTO answersheet (result_id, answer_id) VALUES (?, ?)";
    private static final String DELETE_RESULT = "DELETE FROM tests WHERE id = ?";
    private static final String GET_ANSWERSHEET = "SELECT * FROM answersheet WHERE result_id = ?";

    @Override
    public List<Result> getAll() {
        List<Result> results = new ArrayList<>();
        try(Connection con = ConnectionPool.getInstance().getConnection();
            Statement stmt = con.createStatement()){
            ResultSet rs = stmt.executeQuery(GET_ALL_RESULTS);
            while (rs.next()){
                results.add(new Result(rs.getInt(1),
                        DaoFactory.getUserDao().getById(rs.getInt(2)),
                        DaoFactory.getTestDao().getById(rs.getInt(3)),
                        rs.getInt(4),
                        getAnswersheet(rs.getInt(1))));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return results;
    }

    private static List<Answer> getAnswersheet(int resultId){
        List<Answer> answers = new ArrayList<>();
        try(Connection con = ConnectionPool.getInstance().getConnection();
            PreparedStatement ps = con.prepareStatement(GET_ANSWERSHEET)){
            ps.setInt(1, resultId);
            try(ResultSet rs = ps.executeQuery()){
                while(rs.next()){
                    answers.add(DaoFactory.getAnswerDao().getById(rs.getInt(2)));
                }
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return answers;
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
    public void create(Result result) {
        Connection con = null;
        try{
            con = ConnectionPool.getInstance().getConnection();
            con.setAutoCommit(false);
            int resultId = createNewResult(con, result);
            for(Answer a: result.getAnswersheet()){
                insertIntoAnswersheet(con, resultId, a.getId());
            }
        }catch (SQLException e){
            if(con != null) ConnectionPool.rollback(con);
            e.printStackTrace();
        }finally {
            ConnectionPool.close(con);
        }
    }

    private void insertIntoAnswersheet(Connection con, int resultId, int id) throws SQLException {
        try(PreparedStatement ps = con.prepareStatement(CREATE_ANSWERSHEET)){
            int k = 0;
            ps.setInt(++k, resultId);
            ps.setInt(++k, id);
            if(ps.executeUpdate() ==  0){
                throw new SQLException("Cannot insert into answersheet");
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
