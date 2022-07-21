package com.onyshkevych.model;

import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
@Component
@Entity
@Table(name = "quiz")
public class Quiz {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idquiz;
    @Column
    private String quizques1;
    @Column
    private String quizques2;
    @Column
    private String quizques3;
    @Column
    private String quizques4;
    @Column
    private String quizques5;
    @Column
    private String title;
    public Quiz() {
    }

    public Quiz(String quizques1, String quizques2, String quizques3, String quizques4,
                String quizques5,
                String title) {
        this.quizques1 = quizques1;
        this.quizques2 = quizques2;
        this.quizques3 = quizques3;
        this.quizques4 = quizques4;
        this.quizques5 = quizques5;
        this.title = title;
    }

    public Quiz(int idquiz, String quizques1, String quizques2, String quizques3, String quizques4,
                String quizques5, String title) {
        this.idquiz = idquiz;
        this.quizques1 = quizques1;
        this.quizques2 = quizques2;
        this.quizques3 = quizques3;
        this.quizques4 = quizques4;
        this.quizques5 = quizques5;
        this.title = title;
    }
    public Quiz(List<String> questions, String title) {
        this.quizques1 = questions.get(0);
        this.quizques2 = questions.get(1);
        this.quizques3 = questions.get(2);
        this.quizques4 = questions.get(3);
        this.quizques5 = questions.get(4);
        this.title = title;
    }
    public List<String> putQues() {
        List<String> question = new ArrayList<String>();
        question.add(quizques1);
        question.add(quizques2);
        question.add(quizques3);
        question.add(quizques4);
        question.add(quizques5);
        return question;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getIdquiz() {
        return idquiz;
    }

    public void setIdquiz(int idquiz) {
        this.idquiz = idquiz;
    }

    public String getQuizques1() {
        return quizques1;
    }

    public void setQuizques1(String quizques1) {
        this.quizques1 = quizques1;
    }

    public String getQuizques2() {
        return quizques2;
    }

    public void setQuizques2(String quizques2) {
        this.quizques2 = quizques2;
    }

    public String getQuizques3() {
        return quizques3;
    }

    public void setQuizques3(String quizques3) {
        this.quizques3 = quizques3;
    }

    public String getQuizques4() {
        return quizques4;
    }

    public void setQuizques4(String quizques4) {
        this.quizques4 = quizques4;
    }

    public String getQuizques5() {
        return quizques5;
    }

    public void setQuizques5(String quizques5) {
        this.quizques5 = quizques5;
    }

    @Override
    public String toString() {
        return "Quiz{" +
                "idquiz=" + idquiz +
                ", quizques1=" + quizques1 +
                ", quizques2=" + quizques2 +
                ", quizques3=" + quizques3 +
                ", quizques4=" + quizques4 +
                ", quizques5=" + quizques5 +
                ", title=" + title +
                '}';
    }
}
