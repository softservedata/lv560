package com.hehetenya.test_forms.dto;

import java.util.ArrayList;
import java.util.List;

public class TestDTO {

    private int id;
    private String name;
    private int questionNumber;
    private List<QuestionDTO> questions;

    public TestDTO(int id, String name, List<QuestionDTO> questions) {
        this.id = id;
        this.name = name;
        this.questions = questions;
        countQuestionNumber();
    }

    public TestDTO() {
        this.questions = new ArrayList<>();
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

    public int getQuestionNumber() {
        return questionNumber;
    }

    public List<QuestionDTO> getQuestions() {
        return questions;
    }


    public void setName(String name) {
        this.name = name;
    }

    public void setQuestionNumber(int questionNumber) {
        this.questionNumber = questionNumber;
    }

    public void setQuestions(List<QuestionDTO> questions) {
        this.questions = questions;
    }
}
