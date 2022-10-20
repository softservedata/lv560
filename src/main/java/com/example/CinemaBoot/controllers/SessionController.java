package com.example.CinemaBoot.controllers;

import com.example.CinemaBoot.dto.BookCreate;
import com.example.CinemaBoot.dto.SessionCreate;
import com.example.CinemaBoot.models.*;
import com.example.CinemaBoot.services.BookService;
import com.example.CinemaBoot.services.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/session")
public class SessionController {

    @Autowired
    SessionService sessionService;

    @Autowired
    BookService bookService;

    @GetMapping("/{dateString}")
    public List<Session> getSessionsByDate(@PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) String dateString) {
        return sessionService.getSessionsByDate(dateString);
    }

    @GetMapping("/{dateString}/{timeString}")
    public Session getSessionByDateAndTime(
            @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) String dateString,
                                 @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.TIME) String timeString
    ) {
        return sessionService.getSessionByDateAndTime(dateString, timeString);
    }

    @GetMapping("/{dateString}/{timeString}/seats")
    public List<Seat> getSessionRoomsByDateAndTime(
            @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) String dateString,
            @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.TIME) String timeString
    ) {
        return getSessionByDateAndTime(dateString, timeString).getRoom().getSeats();
    }

    @GetMapping("/dates")
    public Set<Date> getSessionDates() {
        return sessionService.getSessionDates();
    }

//    @PostMapping("/{dateString}/{timeString}/create") public Map<String, Long>
//    createNewSession(
//            @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) String dateString,
//            @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.TIME) String timeString,
//            @RequestBody SessionCreate sessionDTO
//    ) {
//        return bookService.createNewBooking(dateString, timeString, bookDTO);
//    }

    @PostMapping("/{dateString}/{timeString}/book") public Map<String, Long>
    bookNewSession(
            @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) String dateString,
            @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.TIME) String timeString,
            @RequestBody BookCreate bookDTO
    ) {
        return bookService.createNewBooking(dateString, timeString, bookDTO);
    }

}
