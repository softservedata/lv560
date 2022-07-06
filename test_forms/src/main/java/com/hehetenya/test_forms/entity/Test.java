package com.hehetenya.test_forms.entity;

import com.hehetenya.test_forms.dao.impl.DaoFactory;

import java.util.List;

public class Test {
    private int id;
    private String name;
    private int durationMinutes;
    private User creator;
    private List<Question> questions;

    public Test(int id, String name, int durationMinutes, int creatorId, List<Question> questions) {
        this.id = id;
        this.name = name;
        this.durationMinutes = durationMinutes;
        this.creator = DaoFactory.getUserDao().getById(creatorId);
        this.questions = questions;
    }

    public Test(int id, String name, int durationMinutes, List<Question> questions) {
        this.id = id;
        this.name = name;
        this.creator = DaoFactory.getUserDao().getById(1);
        this.durationMinutes = durationMinutes;
        this.questions = questions;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getDurationMinutes() {
        return durationMinutes;
    }

    public User getCreator() {
        return creator;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    @Override
    public String toString() {
        return "Test{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", durationMinutes=" + durationMinutes +
                ", creator=" + creator +
                ", questions=" + questions +
                '}';
    }
}
