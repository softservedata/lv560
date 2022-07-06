package com.hehetenya.test_forms.dto;

import com.hehetenya.test_forms.entity.Question;

import java.util.ArrayList;
import java.util.List;

public class TestDTO {

    private int id;
    private String name;
    private int durationMinutes;
    private String creatorName;
    private int questionNumber;
    private List<QuestionDTO> questions;

    public TestDTO(int id, String name, int durationMinutes, String creatorName, List<QuestionDTO> questions) {
        this.id = id;
        this.name = name;
        this.durationMinutes = durationMinutes;
        this.creatorName = creatorName;
        this.questions = questions;
        countQuestionNumber();
    }

    public TestDTO(String creatorName) {
        this.creatorName = creatorName;
        this.questions = new ArrayList<>();
        this.name = "";
        this.durationMinutes = 1;
    }

    private void countQuestionNumber(){
        questionNumber = questions.size();
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

    public String getCreatorName() {
        return creatorName;
    }

    public int getQuestionNumber() {
        return questionNumber;
    }

    public List<QuestionDTO> getQuestions() {
        return questions;
    }


    public void setName(String name) {
        this.name = name;
    }

    public void setDurationMinutes(int durationMinutes) {
        this.durationMinutes = durationMinutes;
    }

    public void setCreatorName(String creatorName) {
        this.creatorName = creatorName;
    }

    public void setQuestionNumber(int questionNumber) {
        this.questionNumber = questionNumber;
    }

    public void setQuestions(List<QuestionDTO> questions) {
        this.questions = questions;
    }
}
