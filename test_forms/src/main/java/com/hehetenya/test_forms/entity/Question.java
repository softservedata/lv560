package com.hehetenya.test_forms.entity;

import com.hehetenya.test_forms.dao.impl.DaoFactory;
import com.hehetenya.test_forms.exeptions.AppException;
import com.hehetenya.test_forms.exeptions.DBException;

import java.util.ArrayList;
import java.util.List;

public class Question {
    private int id;
    private String text;
    private int points;
    private List<Option> options;

    public Question(int id, String text, int points) {
        this.id = id;
        this.text = text;
        this.points = points;
        options = new ArrayList<>();
        setOptions();
    }

    public Question(String text, int points) {
        this.text = text;
        this.points = points;
        options = new ArrayList<>();
        setOptions();
    }

    public Question(String text, int points, List<Option> options) {
        this.text = text;
        this.points = points;
        this.options = options;
    }

    private void setOptions(){
        try {
            List<Option> allOptions = DaoFactory.getAnswerDao().getAll();
            for (Option o : allOptions) {
                if (o.getQuestionId() == this.id) {
                    options.add(o);
                }
            }
        }catch (DBException e){
            throw new AppException();
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

    public List<Option> getAnswers() {
        return options;
    }
}
