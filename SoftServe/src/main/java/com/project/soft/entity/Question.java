package com.project.soft.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.*;

@Setter
@Getter
@ToString(exclude = {"answers", "tests"})
@Table(name = "questions")
@Entity
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String title;
    @Column(length = 1000)
    private String description;
    @ManyToOne
    private User user;
    @ManyToOne
    private QuestionType type;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "questions_tests",
            joinColumns = @JoinColumn(name = "question_id"),
            inverseJoinColumns = @JoinColumn(name = "test_id")
    )
    private Set<Test> tests = new HashSet<>();
    @Setter(AccessLevel.PRIVATE)
    @OneToMany(mappedBy="question",
               fetch = FetchType.EAGER,
               cascade = {CascadeType.REFRESH, CascadeType.PERSIST, CascadeType.MERGE})
    private List<Answer> answers = new ArrayList<>();
    @ManyToOne
    private Category category;

    public void addAnswer(Answer answer) {
        answers.add(answer);
        answer.setQuestion(this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Question question = (Question) o;

        return title.equals(question.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title);
    }
}
