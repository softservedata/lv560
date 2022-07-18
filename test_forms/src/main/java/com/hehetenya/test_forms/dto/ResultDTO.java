package com.hehetenya.test_forms.dto;

import java.util.List;

public class ResultDTO {
    private int id;
    private UserDTO user;
    private TestDTO test;
    private int grade;
    private List<OptionDTO> answers;

    public ResultDTO(int id, UserDTO user, TestDTO test, int grade, List<OptionDTO> answers) {
        this.id = id;
        this.user = user;
        this.test = test;
        this.grade = grade;
        this.answers = answers;
    }

    public ResultDTO(UserDTO user, TestDTO test, List<OptionDTO> answers) {
        this.user = user;
        this.test = test;
        this.answers = answers;
    }

    public UserDTO getUser() {
        return user;
    }

    public TestDTO getTest() {
        return test;
    }

    public int getGrade() {
        return grade;
    }

    public List<OptionDTO> getAnswers() {
        return answers;
    }

    public int getId() {
        return id;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }
}
