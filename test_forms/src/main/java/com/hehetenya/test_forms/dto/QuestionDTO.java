package com.hehetenya.test_forms.dto;

import java.util.List;

public class QuestionDTO {
    private String text;
    private int points;
    private List<OptionDTO> options;

    public QuestionDTO(String text, int points, List<OptionDTO> options) {
        this.text = text;
        this.points = points;
        this.options = options;
    }

    public String getText() {
        return text;
    }

    public int getPoints() {
        return points;
    }

    public List<OptionDTO> getOptions() {
        return options;
    }
}
