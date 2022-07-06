package com.hehetenya.test_forms.dao.impl;

public class DaoFactory {

    public static UserDaoImpl getUserDao() {
        return new UserDaoImpl();
    }

    public static AnswerDaoImpl getAnswerDao() {
        return new AnswerDaoImpl();
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
