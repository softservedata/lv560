package com.project.soft.entity;

import com.project.soft.util.converters.LocalDateTimeAttributeConverter;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@Table(name = "results")
@Entity
public class Result {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    @ManyToOne
    private Test test;
    @ManyToOne
    private User user;
    private Integer grade;
    @Convert(converter = LocalDateTimeAttributeConverter.class)
    @Column(name = "test_finish_time")
    private LocalDateTime testFinishTime;
    @Convert(converter = LocalDateTimeAttributeConverter.class)
    @Column(name = "test_start_time")
    private LocalDateTime testStartTime;
    @Setter(AccessLevel.PRIVATE)
    @OneToMany(mappedBy = "result",
               fetch = FetchType.EAGER,
               cascade = {CascadeType.REFRESH, CascadeType.MERGE, CascadeType.PERSIST})
    private List<QuestionHistory> questionHistories = new ArrayList<>();

    public void addQuestionHistory(QuestionHistory questionHistory) {
        questionHistories.add(questionHistory);
        questionHistory.setResult(this);
    }
}
