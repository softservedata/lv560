package com.hehetenya.test_forms.entity;

import java.util.List;

public class Result {
    private int id;
    private User user;
    private Test test;
    private int grade;
    private List<Option> answers;

    public Result(int id, User user, Test test, int grade, List<Option> answers) {
        this.id = id;
        this.user = user;
        this.test = test;
        this.grade = grade;
        this.answers = answers;
    }

    public Result(User user, Test test, int grade, List<Option> answers) {
        this.user = user;
        this.test = test;
        this.grade = grade;
        this.answers = answers;
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

    public List<Option> getAnswers() {
        return answers;
    }
}
