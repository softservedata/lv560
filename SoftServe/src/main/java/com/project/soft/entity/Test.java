package com.project.soft.entity;

import com.project.soft.util.converters.LocalDateAttributeConverter;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Table(name = "tests")
@Entity
public class Test {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String title;
    private int duration;
    private int grade;
    private boolean archived;
    @Column(name = "active")
    private boolean active;
    @Column(name = "picture_url")
    private String pictureUrl;
    @Column(length = 1000)
    private String description;
    @Column(name = "passage_counter")
    private int passageCounter;
    @Convert(converter = LocalDateAttributeConverter.class)
    @Column(name = "date_of_creation")
    private LocalDate dateOfCreation;
    @ManyToOne
    private User creator;
    @ManyToMany(mappedBy = "tests",
                cascade = {CascadeType.REFRESH, CascadeType.ALL})
    @Setter(AccessLevel.PRIVATE)
    private Set<Topic> topics = new HashSet<>();
    @Setter(AccessLevel.PRIVATE)
    @ManyToMany(mappedBy = "tests",
                cascade = {CascadeType.REFRESH})
    private Set<Question> questions = new HashSet<>();
    @Setter(AccessLevel.PRIVATE)
    @OneToMany(mappedBy = "test",
                fetch = FetchType.EAGER,
                cascade = {CascadeType.REFRESH, CascadeType.ALL})
    private Set<Like> likes = new HashSet<>();
    @Setter(AccessLevel.PRIVATE)
    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Group> groups = new HashSet<>();

    public void addQuestion(Question question) {
        questions.add(question);
        question.getTests().add(this);
    }

    public void increasePassageCounter() {
        passageCounter++;
    }

    public void addTopics(List<Topic> topics) {
        for (Topic topic : topics) {
            topic.getTests().add(this);
            this.topics.add(topic);
        }
    }
}