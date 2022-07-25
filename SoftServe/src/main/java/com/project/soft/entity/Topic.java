package com.project.soft.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Table(name = "topics")
@Entity
public class Topic {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    @NotBlank(message = "This field can't be empty")
    @Size(min = 4, max = 20, message = "Title should be between 4 and 20 characters")
    private String title;
    @NotBlank(message = "This field can't be empty")
    private String pictureUrl;
    @Setter(AccessLevel.PRIVATE)
    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Test> tests = new HashSet<>();
}
