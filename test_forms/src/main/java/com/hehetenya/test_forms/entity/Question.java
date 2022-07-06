package com.hehetenya.test_forms.entity;

import com.hehetenya.test_forms.dao.impl.DaoFactory;

import java.util.ArrayList;
import java.util.List;

public class Question {
    private int id;
    private String text;
    private int points;
    private List<Answer> answers;

    public Question(int id, String text, int points) {
        this.id = id;
        this.text = text;
        this.points = points;
        answers = new ArrayList<>();
        setAnswers();
    }

    public Question(String text, int points) {
        this.text = text;
        this.points = points;
        answers = new ArrayList<>();
        setAnswers();
    }

    public Question(String text, int points, List<Answer> answers) {
        this.text = text;
        this.points = points;
        this.answers = answers;
    }

    private void setAnswers(){
        List<Answer> allAnswers = DaoFactory.getAnswerDao().getAll();
        for (Answer a: allAnswers) {
            if(a.getQuestionId() == this.id){
                answers.add(a);
            }
        }
    }

    public int getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    public int getPoints() {
        return points;
    }

    public List<Answer> getAnswers() {
        return answers;
    }
}
