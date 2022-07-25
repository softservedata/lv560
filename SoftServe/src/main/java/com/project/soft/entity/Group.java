package com.project.soft.entity;

import com.project.soft.util.converters.LocalDateAttributeConverter;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Getter
@Setter
@Table(name = "groups")
@Entity
public class Group {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    @NotBlank(message = "This field can't be empty")
    @Size(min = 4, max = 20, message = "Title should be between 4 and 20 characters")
    private String title;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Convert(converter = LocalDateAttributeConverter.class)
    @Column(name = "start_date")
    private LocalDate startDate;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Convert(converter = LocalDateAttributeConverter.class)
    @Column(name = "end_date")
    private LocalDate endDate;
    @Setter(AccessLevel.PRIVATE)
    @ManyToMany(mappedBy = "groups", fetch = FetchType.EAGER)
    private Set<Test> tests = new HashSet<>();

    public void addTest(Test test) {
        tests.add(test);
        test.getGroups().add(this);
    }

    public void removeTest(Test test) {
        tests.remove(test);
        test.getGroups().remove(this);
    }

    public boolean containsTestId(long id) {
        return tests.stream()
                .mapToLong(Test::getId)
                .anyMatch(x -> x == id);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Group group = (Group) o;
        return id.equals(group.id) &&
                title.equals(group.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title);
    }
}