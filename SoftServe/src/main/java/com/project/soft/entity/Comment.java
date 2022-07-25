package com.project.soft.entity;

import com.project.soft.util.converters.LocalDateAttributeConverter;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Table(name = "comments")
@Entity
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Convert(converter = LocalDateAttributeConverter.class)
    @Column(name = "date_of_creation")
    private LocalDate dateOfCreation;

    private String content;

    @Column(name = "answer_to")
    private String answerTo;

    @ManyToOne
    private User user;

    @ManyToOne
    private Test test;

    @OneToMany(mappedBy = "parent",
               fetch = FetchType.EAGER)
    @Setter(AccessLevel.PRIVATE)
    private Set<Comment> children = new HashSet<>();

    @ManyToOne
    private Comment parent;
}
