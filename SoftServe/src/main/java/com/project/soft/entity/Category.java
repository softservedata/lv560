package com.project.soft.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Table(name = "categories")
@Entity
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    @NotBlank(message = "This field can't be empty")
    @Size(min = 4, max = 20, message = "Title should be between 4 and 20 characters")
    private String title;
    @Setter(AccessLevel.PRIVATE)
    @OneToMany(mappedBy = "category")
    private Set<Question> questions = new HashSet<>();

    public Category(String title) {
        this.title = title;
    }
}
