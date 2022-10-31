package org.pupa.models;

import javax.persistence.*;

@Entity()
public class Question {
    @Id()
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column( nullable = false, length = 255)
    private String question;

    @Column(nullable = false, length = 255)
    private String key;

    public Question() {
    }

    public Question(Long id, String question, String key) {
        this.id = id;
        this.question = question;
        this.key = key;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    @Override
    public String toString() {
        return "Question{" +
                ", question='" + question + '\'' +
                ", key='" + key + '\'' +
                '}';
    }
}
