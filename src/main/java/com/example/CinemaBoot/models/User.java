package com.example.CinemaBoot.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String email;

    @Getter(onMethod_=@JsonIgnore)
    private String password;

    @Getter(onMethod_=@JsonIgnore)
    private boolean isAdmin;

    @OneToMany(mappedBy = "user")
    private List<Book> books;

}
