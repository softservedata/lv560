package com.hehetenya.test_forms.entity;

import java.util.List;

public class Result {
    private int id;
    private User user;
    private Test test;
    private int grade;
    private List<Answer> answersheet;

    public Result(int id, User user, Test test, int grade, List<Answer> answersheet) {
        this.id = id;
        this.user = user;
        this.test = test;
        this.grade = grade;
        this.answersheet = answersheet;
    }

    public Result(User user, Test test, int grade, List<Answer> answersheet) {
        this.user = user;
        this.test = test;
        this.grade = grade;
        this.answersheet = answersheet;
    }


    public int getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public Test getTest() {
        return test;
    }

    public int getGrade() {
        return grade;
    }

    public List<Answer> getAnswersheet() {
        return answersheet;
    }
}
