package com.project.soft.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;


@Getter
@Setter
@Table(name = "answers")
@Entity
public class Answer {


    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String text;
    @ManyToOne
    @JoinColumn(name = "question_id")
    private Question question;
    private int value;
    @Column(name = "correct")
    private Boolean correct;
}
