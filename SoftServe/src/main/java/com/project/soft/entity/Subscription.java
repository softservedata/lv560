package com.project.soft.entity;

import com.project.soft.util.converters.LocalDateAttributeConverter;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Setter
@Table(name = "subscriptions")
@Entity
public class Subscription {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    @ManyToOne
    private User user;
    @ManyToOne
    private Group group;
    @Convert(converter = LocalDateAttributeConverter.class)
    @Column(name = "end_date")
    private LocalDate endDate;
}
