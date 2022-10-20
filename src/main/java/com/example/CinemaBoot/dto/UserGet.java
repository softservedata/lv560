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

    private String email;

    private List<BookGet> books;

    public UserGet(User user) {
        this.email = user.getEmail();
        this.books = user.getBooks().stream().map(BookGet::new).collect(Collectors.toList());
    }

}
