package com.project.soft.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@Setter
@Getter
@Table(name = "users")
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    @Size(min = 2, max = 30, message = "Username should be between 2 and 30 characters")
    @NotBlank(message = "This field can't be empty")
    private String username;
    @Size(min = 10, max = 30, message = "Username should be between 10 and 30 characters")
    @NotBlank(message = "This field can't be empty")
    private String password;
    @Email(message = "Invalid email")
    @NotBlank(message = "This field can't be empty")
    @Size(max = 30, message = "Username should be between 10 and 30 characters")
    private String email;
    @NotBlank(message = "This field can't be empty")
    private String pictureUrl;
    @ManyToOne
    private Role role;
    @OneToMany(mappedBy = "user")
    private Set<Subscription> subscriptions = new HashSet<>();

    @Override
    public String toString() {
        return username;
    }
}
