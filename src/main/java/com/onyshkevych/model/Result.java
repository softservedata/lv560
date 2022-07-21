package com.onyshkevych.model;


import javax.persistence.*;

import org.springframework.stereotype.Component;

@Component
@Entity
@Table(name = "results")
public class Result {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String username;
    private int total_correct = 0;

//    @ManyToOne
//    @JoinColumn(name = "username")
//    private User user;

    public Result() {
        super();
    }

    public Result(int id, String username, int total_correct) {
        super();
        this.id = id;
        this.username = username;
        this.total_correct = total_correct;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getTotal_correct() {
        return total_correct;
    }

    public void setTotal_correct(int totalCorrect) {
        this.total_correct = totalCorrect;
    }

}
