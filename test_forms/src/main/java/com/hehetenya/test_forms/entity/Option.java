package com.hehetenya.test_forms.entity;

public class Option {
    private int id;
    private String text;
    private boolean isCorrect;
    private int questionId;

    public Option(int id, String text, boolean isCorrect, int question_id) {
        this.id = id;
        this.text = text;
        this.isCorrect = isCorrect;
        this.questionId = question_id;
    }

    public Option(int id, String text, boolean isCorrect) {
        this.id = id;
        this.text = text;
        this.isCorrect = isCorrect;
    }

    public Option(String text, boolean isCorrect) {
        this.text = text;
        this.isCorrect = isCorrect;
    }

    public int getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    public boolean isCorrect() {
        return isCorrect;
    }

    public int getQuestionId() {
        return questionId;
    }
}
