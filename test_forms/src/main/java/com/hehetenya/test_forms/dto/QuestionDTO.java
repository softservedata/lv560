package com.hehetenya.test_forms.dto;

import com.hehetenya.test_forms.entity.Question;

import java.util.List;

public class QuestionDTO {
    private String text;
    private int points;
    private List<AnswerDTO> answers;

    public QuestionDTO(String text, int points, List<AnswerDTO> answers) {
        this.text = text;
        this.points = points;
        this.answers = answers;
    }

    public String getText() {
        return text;
    }

    public int getPoints() {
        return points;
    }

    public List<AnswerDTO> getAnswers() {
        return answers;
    }
}
