package com.example.CinemaBoot.dto;

import com.example.CinemaBoot.models.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserGet {

    private long id;

    private String email;

    String gender;

    private List<BookGet> books;

    public UserGet(User user) {
        this.id = user.getId();
        this.email = user.getEmail();
        this.gender = user.getGender().name();
        this.books = user.getBooks().stream().map(BookGet::new).collect(Collectors.toList());
    }

}
