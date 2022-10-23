package com.example.CinemaBoot.dto.book;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookCreate {

    private long userId;

    private long roomId;

    List<Integer> seats;

}
