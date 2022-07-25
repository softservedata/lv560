package com.project.soft.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter
@Table(name = "question_histories")
@Entity
public class QuestionHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "result_id")
    private Result result;
    @ManyToOne
    private Answer answer;
}
