package com.onyshkevych.model;

import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Component
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int user_id;

    private String name;
    private String password;
    private int user_role;


//    @OneToMany(mappedBy = "user")
//    private List<Result> results;

    public User(int user_id, String name, String password, int user_role) {
        this.user_id = user_id;
        this.name = name;
        this.password = password;
        this.user_role = user_role;
    }

    public User(String name, String password, int user_role) {
        this.name = name;
        this.password = password;
        this.user_role = user_role;
    }

    public User() {
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getUser_role() {
        return user_role;
    }

    public void setUser_role(int user_role) {
        this.user_role = user_role;
    }

    @Override
    public String toString() {
        return "User{" +
                "user_id=" + user_id +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", user_role=" + user_role +
                '}';
    }
}
