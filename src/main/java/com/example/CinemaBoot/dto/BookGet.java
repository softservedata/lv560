package com.example.CinemaBoot.dto;

import com.example.CinemaBoot.models.Book;
import com.example.CinemaBoot.models.Seat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookGet {

    private String roomName;

    private Date date;

    private Date time;

    private List<Integer> seatNumbers;

    public BookGet(Book book) {
        this.roomName = book.getSession().getRoom().getName();
        this.date = book.getSession().getDate();
        this.time = book.getSession().getTime();
        this.seatNumbers = book.getSeats().stream().map(Seat::getNumber).collect(Collectors.toList());
    }

}
