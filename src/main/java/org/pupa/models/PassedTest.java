package org.pupa.models;

import javax.persistence.*;
import java.util.List;

@Entity
public class PassedTest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private Test test;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL,
            orphanRemoval = true)
    private List<Answer> answers = new java.util.ArrayList<>();

    public PassedTest() {
    }

    public PassedTest(Long id, Test test, List<Answer> answers) {
        this.id = id;
        this.test = test;
        this.answers = answers;
    }

    public List<Answer> getAnswers() {
        return answers;
    }

    public void setAnswers(List<Answer> answers) {
        this.answers = answers;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Test getTest() {
        return test;
    }

    public String getScore(){
        System.out.println("i'm HERE");
        int correctAnswers = 0;
        System.out.println(test.getName());
        System.out.println(test.getQuestions().size());
        System.out.println(answers.size());
        for(int i = 0 ; i < test.getQuestions().size() ; i++){
            Question currentQuestion = getTest().getQuestions().get(i);
            Answer currentAnswer = getAnswers().get(i);
            if(currentQuestion.getKey().equalsIgnoreCase(currentAnswer.getAnswer())){
                correctAnswers++;
            }
        }
        System.out.println(correctAnswers + "/" + test.getQuestions().size()    );
        return correctAnswers + "/" + test.getQuestions().size();
    }

    public boolean isCorrect(int questionNumber){
        if(questionNumber >= test.getQuestions().size() || questionNumber < 0){
            throw new IndexOutOfBoundsException("This question doesn't exist!");
        }
        return getTest().getQuestions().get(questionNumber).getKey().equalsIgnoreCase(getAnswers().get(questionNumber).getAnswer());
    }

    public void setTest(Test test) {
        this.test = test;
    }
}
