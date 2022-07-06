package com.hehetenya.test_forms.dto;

import java.util.List;

public class ResultDTO {
    private int id;
    private UserDTO user;
    private TestDTO test;
    private int grade;
    private List<AnswerDTO> answersheet;

    public ResultDTO(int id, UserDTO user, TestDTO test, int grade, List<AnswerDTO> answersheet) {
        this.id = id;
        this.user = user;
        this.test = test;
        this.grade = grade;
        this.answersheet = answersheet;
    }

    public ResultDTO(UserDTO user, TestDTO test, List<AnswerDTO> answersheet) {
        this.user = user;
        this.test = test;
        this.answersheet = answersheet;
        countGrade();
    }

    private void countGrade() {
        double maxPointsByTest = 0;
        double actualPointByTest = 0;
        for (QuestionDTO q : test.getQuestions()) {
            double numberOfChoices = q.getAnswers().size();
            double numbOfCorrect = 0;
            double numbOfIncorrect = 0;

            for (AnswerDTO a : q.getAnswers()) {
                if (a.isCorrect() && answersheet.contains(a)) {
                    ++numbOfCorrect;
                } else if (a.isCorrect() && !answersheet.contains(a)) {
                    ++numbOfIncorrect;
                } else if (!a.isCorrect() && answersheet.contains(a)) {
                    ++numbOfIncorrect;
                } else if (!a.isCorrect() && !answersheet.contains(a)) {
                    ++numbOfCorrect;
                }
            }
            maxPointsByTest += q.getPoints();
            actualPointByTest += numbOfCorrect  * q.getPoints() / numberOfChoices;
            System.out.println("maxpoints ==> " + maxPointsByTest);
            System.out.println("actual ==> " + actualPointByTest);
        }
        grade = (int) (actualPointByTest/maxPointsByTest * 100);

        System.out.println("grade ==> " + grade);
        if (grade < 0) grade = 0;
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

    public List<AnswerDTO> getAnswersheet() {
        return answersheet;
    }

    public int getId() {
        return id;
    }
}
