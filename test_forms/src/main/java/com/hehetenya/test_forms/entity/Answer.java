package com.hehetenya.test_forms.entity;

public class Answer {
    private int id;
    private String text;
    private boolean correct;
    private int questionId;

    public Answer(int id, String text, boolean is_correct, int question_id) {
        this.id = id;
        this.text = text;
        this.correct = is_correct;
        this.questionId = question_id;
    }

    public Answer(int id, String text, boolean is_correct) {
        this.id = id;
        this.text = text;
        this.correct = is_correct;
    }

    public Answer(String text, boolean correct) {
        this.text = text;
        this.correct = correct;
    }

    public int getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    public boolean isCorrect() {
        return correct;
    }

    public int getQuestionId() {
        return questionId;
    }
}
