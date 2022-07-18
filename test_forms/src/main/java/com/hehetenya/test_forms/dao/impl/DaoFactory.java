package com.hehetenya.test_forms.dao.impl;

/**
 * Class to get every entity PostgresSQL DAO implementation.
 */
public class DaoFactory {

    public static UserDaoImpl getUserDao() {
        return new UserDaoImpl();
    }

    public static OptionDaoImpl getAnswerDao() {
        return new OptionDaoImpl();
    }

    public static QuestionDaoImpl getQuestionDao() {
        return new QuestionDaoImpl();
    }

    public static ResultDaoImpl getResultDao() {
        return new ResultDaoImpl();
    }

    public static TestDaoImpl getTestDao() {
        return new TestDaoImpl();
    }
}
