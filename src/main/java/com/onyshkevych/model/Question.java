package com.onyshkevych.model;


import javax.persistence.*;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Component
@Entity
@Table(name = "questions")
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int ques_id;
    @Column
    private String title;
    @Column
    private String optionA;
    @Column
    private String optionB;
    @Column
    private String optionC;
    @Column
    private String ans;
    @Column
    private String chose;
    @Column
    private String question_type;

    public Question() {
        super();
    }

    public Question(int ques_id, String title, String optionA, String optionB, String optionC, String ans, String chose, String questionType) {
        super();
        this.ques_id = ques_id;
        this.title = title;
        this.optionA = optionA;
        this.optionB = optionB;
        this.optionC = optionC;
        this.ans = ans;
        this.chose = chose;
        this.question_type = questionType;

    }

    public int getQues_id() {
        return ques_id;
    }

    public void setQues_id(int quesId) {
        this.ques_id = quesId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getOptionA() {
        return optionA;
    }

    public void setOptionA(String optionA) {
        this.optionA = optionA;
    }

    public String getOptionB() {
        return optionB;
    }

    public void setOptionB(String optionB) {
        this.optionB = optionB;
    }

    public String getOptionC() {
        return optionC;
    }

    public void setOptionC(String optionC) {
        this.optionC = optionC;
    }

    public String getAns() {
        return ans;
    }

    public void setAns(String ans) {
        this.ans = ans;
    }

    public String getChose() {
        return chose;
    }

    public void setChose(String choosed) {
        this.chose = choosed;
    }

    @Column(name = "question_type")
    public String getQuestion_type() {
        return question_type;
    }

    public void setQuestion_type(String questionType) {
        this.question_type = questionType;
    }
    public void shuffleAnswers(){
        List<String> answers = new ArrayList<String>();
        answers.add(optionA);
        answers.add(optionB);
        answers.add(optionC);
        Collections.shuffle(answers);
        optionA = answers.get(0);
        optionB = answers.get(1);
        optionC = answers.get(2);
    }

    @Override
    public String toString() {
        return "Question {ques_id=" + ques_id + ", title=" + title + ", optionA=" + optionA + ", optionB=" + optionB + ", optionC=" + optionC + ", ans=" + ans + ", chose=" + chose + ", question_type=" + question_type +"]";
    }

}

