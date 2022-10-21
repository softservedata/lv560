package com.example.CinemaBoot.controllers;

import com.example.CinemaBoot.dto.BookCreate;
import com.example.CinemaBoot.dto.SessionCreate;
import com.example.CinemaBoot.models.*;
import com.example.CinemaBoot.services.BookService;
import com.example.CinemaBoot.services.SessionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/session")
public class SessionController {

    Logger logger = LoggerFactory.getLogger(SessionController.class);

    @Autowired
    SessionService sessionService;

    @Autowired
    BookService bookService;

    @GetMapping("/{dateString}")
    public List<Session> getSessionsByDate(@PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) String dateString) {
        logger.info("GET /api/session/" + dateString);
        return sessionService.getSessionsByDate(dateString);
    }

    @GetMapping("/{dateString}/{timeString}")
    public Session getSessionByDateAndTime(
            @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) String dateString,
                                 @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.TIME) String timeString
    ) {
        logger.info("GET /api/session/" + dateString + "/" + timeString);
        return sessionService.getSessionByDateAndTime(dateString, timeString);
    }

    @GetMapping("/{dateString}/{timeString}/seats")
    public List<Seat> getSessionRoomsByDateAndTime(
            @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) String dateString,
            @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.TIME) String timeString
    ) {
        logger.info("GET /api/session/" + dateString + "/" + timeString + "/seats");
        return getSessionByDateAndTime(dateString, timeString).getRoom().getSeats();
    }

    @GetMapping("/dates")
    public Set<Date> getSessionDates() {
        logger.info("GET /api/session/dates");
        return sessionService.getSessionDates();
    }

    @PostMapping("/{dateString}/{timeString}/create") public Map<String, Long>
    createNewSession(
            @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) String dateString,
            @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.TIME) String timeString,
            @RequestBody SessionCreate sessionDTO
    ) {
        logger.info("POST /api/session/" + dateString + "/" + timeString + "/create, session=" + sessionDTO);
        return sessionService.createNewSession(dateString, timeString, sessionDTO);
    }

    @PostMapping("/{dateString}/{timeString}/book") public Map<String, Long>
    bookNewSession(
            @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) String dateString,
            @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.TIME) String timeString,
            @RequestBody BookCreate bookDTO
    ) {
        logger.info("POST /api/session/" + dateString + "/" + timeString + "/book, book=" + bookDTO);
        return bookService.createNewBooking(dateString, timeString, bookDTO);
    }

}
