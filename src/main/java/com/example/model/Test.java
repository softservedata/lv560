package com.example.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name = "tests")
public class Test {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "test_name", nullable = false, length = 50)
    private String testName;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "creator_id", nullable = false)
    private User creator;

    @Column(name = "test_description", length = 50)
    private String testDescription;

    @Column(name = "test_theme", nullable = false, length = 50)
    private String testTheme;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "test")
    private Set<Question> questions = new LinkedHashSet<>();

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "test")
    private Set<Result> results = new LinkedHashSet<>();
}