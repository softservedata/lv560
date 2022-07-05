package com.example.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.LinkedHashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "questions")
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "test_id", nullable = false)
    private Test test;

    @Column(name = "question_text", nullable = false, length = 50)
    private String questionText;

    @OneToMany(mappedBy = "question")
    private Set<Answer> answers = new LinkedHashSet<>();

    @Column(name = "question_mark", nullable = false, precision = 4, scale = 2)
    private BigDecimal questionMark;

    public BigDecimal getQuestionMark() {
        return questionMark;
    }

    public void setQuestionMark(BigDecimal questionMark) {
        this.questionMark = questionMark;
    }
}