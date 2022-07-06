package com.hehetenya.test_forms.dto;

import java.util.Objects;

public class AnswerDTO {
    private int id;
    private String text;
    private boolean correct;

    public AnswerDTO(int id, String text, boolean correct) {
        this.id = id;
        this.text = text;
        this.correct = correct;
    }

    public AnswerDTO(String text, boolean correct) {
        this.text = text;
        this.correct = correct;
    }

    public int getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AnswerDTO answerDTO = (AnswerDTO) o;
        return id == answerDTO.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public String getText() {
        return text;
    }

    public boolean isCorrect() {
        return correct;
    }
}
