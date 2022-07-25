package com.project.soft.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter
@NoArgsConstructor
@Table(name = "question_types")
@Entity
public class QuestionType {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String questionType;

    public QuestionType(String questionType) {
        this.questionType = questionType;
    }

    @Override
    public String toString() {
        return questionType;
    }
}
