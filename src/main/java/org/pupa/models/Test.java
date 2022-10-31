package org.pupa.models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Test {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 255)
    private String name;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Question> questions = new ArrayList<>();

    public Test() {
    }

    public Test(Long id, String name, List<Question> questions) {
        this.id = id;
        this.name = name;
        this.questions = questions;
    }

    public Test(String name, int size ) {
        this.name = name;
        this.questions = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            questions.add(new Question());
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public int getAmountOfQuestions(){
        return questions.size();
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }

    public void setEmptyQuestions(int amount) {
        for (int i = 0; i < amount; i++) {
            questions.add(new Question());
        }
    }
}
