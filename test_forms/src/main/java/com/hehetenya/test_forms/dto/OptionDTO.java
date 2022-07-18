package com.hehetenya.test_forms.dto;

import java.util.Objects;

public class OptionDTO {
    private int id;
    private String text;
    private boolean correct;

    public OptionDTO(int id, String text, boolean correct) {
        this.id = id;
        this.text = text;
        this.correct = correct;
    }

    public OptionDTO(String text, boolean correct) {
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
        OptionDTO optionDTO = (OptionDTO) o;
        return id == optionDTO.id;
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
