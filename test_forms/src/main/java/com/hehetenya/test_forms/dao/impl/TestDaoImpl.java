package com.hehetenya.test_forms.dao.impl;

import com.hehetenya.test_forms.dao.ConnectionPool;
import com.hehetenya.test_forms.dao.Dao;
import com.hehetenya.test_forms.entity.Question;
import com.hehetenya.test_forms.entity.Test;
import com.hehetenya.test_forms.exeptions.DBException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Dao interface implementation for PostgresSQL database using JDBC API
 * that performs CRUD operations on objects of type Test.
 */
public class TestDaoImpl implements Dao<Test> {

    private static final String GET_ALL_TESTS = "SELECT * FROM tests";
    private static final String CREATE_TEST = "INSERT INTO tests (name) VALUES (?)";
    private static final String GET_TEST_QUESTIONS = "SELECT * FROM tests_questions WHERE test_id = ?";
    private static final String CREATE_TEST_HAS_QUESTION = "INSERT INTO tests_questions (test_id, question_id) VALUES (?, ?)";


    @Override
    public List<Test> getAll() throws DBException {
        List<Test> tests = new ArrayList<>();
        try(Connection con = ConnectionPool.getInstance().getConnection();
            Statement stmt = con.createStatement()){
            ResultSet rs = stmt.executeQuery(GET_ALL_TESTS);
            while (rs.next()){
                tests.add(new Test(rs.getInt(1),
                        rs.getString(2),
                        getTestQuestions(rs.getInt(1))));
            }
        }catch (SQLException e){
            throw new DBException("Cannot get all tests", e);
        }
        return tests;
    }

    private List<Question> getTestQuestions(int testId) throws DBException {
        List<Question> questions = new ArrayList<>();
        try(Connection con = ConnectionPool.getInstance().getConnection();
            PreparedStatement ps = con.prepareStatement(GET_TEST_QUESTIONS)){
            ps.setInt(1, testId);
            try(ResultSet rs = ps.executeQuery()){
                while(rs.next()){
                    questions.add(DaoFactory.getQuestionDao().getById(rs.getInt(2)));
                }
            }
        }catch (SQLException e){
            throw new DBException("Cannot get test questions", e);
        }
        return questions;
    }

    @Override
    public Test getById(int id) throws DBException {
        List<Test> tests = getAll();
        for (Test t:
             tests) {
            if(t.getId() == id){
                return t;
            }
        }
        return null;
    }

    @Override
    public void update(Test test) {

    }

    @Override
    public void delete(Test test) {

    }

    @Override
    public void create(Test test) throws DBException {
        Connection con = null;
        try{
            con = ConnectionPool.getInstance().getConnection();
            con.setAutoCommit(false);
            int testId = insertTest(con, test);
            for(Question q: test.getQuestions()){
                int questionId = DaoFactory.getQuestionDao().insertQuestion(con, q);
                insertTestHasQuestions(con, testId, questionId);
            }
            con.commit();
        }catch (SQLException e){
            if(con != null) ConnectionPool.rollback(con);
            throw new DBException("Cannot create test ", e);
        }finally {
            ConnectionPool.close(con);
        }
    }

    private int insertTest(Connection con, Test test) throws SQLException {
        try(PreparedStatement ps = con.prepareStatement(CREATE_TEST, Statement.RETURN_GENERATED_KEYS)){
            int k = 0;
            ps.setString(++k, test.getName());
            if(ps.executeUpdate() == 0){
                throw new SQLException("Cannot create new test");
            }
            try(ResultSet rs = ps.getGeneratedKeys()){
                if(rs.next()){
                    return rs.getInt(1);
                }
            }
        }
        return 0;
    }

    private void insertTestHasQuestions(Connection con, int testId, int questionId) throws SQLException {
        try(PreparedStatement ps = con.prepareStatement(CREATE_TEST_HAS_QUESTION)){
            int k = 0;
            ps.setInt(++k, testId);
            ps.setInt(++k, questionId);
            if(ps.executeUpdate() == 0){
                throw new SQLException("Cannot create new test");
            }
        }
    }
}
