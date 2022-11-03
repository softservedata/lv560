package com.example.CinemaBoot.controllers;

import com.example.CinemaBoot.dto.book.BookCreate;
import com.example.CinemaBoot.dto.seat.SeatGet;
import com.example.CinemaBoot.dto.session.SessionCreate;
import com.example.CinemaBoot.dto.session.SessionGet;
import com.example.CinemaBoot.models.*;
import com.example.CinemaBoot.services.BookService;
import com.example.CinemaBoot.services.SessionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/session")
public class SessionController {

    Logger logger = LoggerFactory.getLogger(SessionController.class);

    @Autowired
    SessionService sessionService;

    @Autowired
    BookService bookService;

    @GetMapping("/{dateString}")
    public List<SessionGet> getSessionsByDate(@PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) String dateString) {
        logger.info("GET /api/session/" + dateString);
        return sessionService.getSessionsByDate(dateString);
    }

    @GetMapping("/{dateString}/{timeString}")
    public SessionGet getSessionByDateAndTime(
            @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) String dateString,
                                 @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.TIME) String timeString
    ) {
        logger.info("GET /api/session/" + dateString + "/" + timeString);
        return sessionService.getSessionByDateAndTime(dateString, timeString);
    }

    @GetMapping("/{dateString}/{timeString}/seats")
    public List<SeatGet> getSessionRoomsByDateAndTime(
            @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) String dateString,
            @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.TIME) String timeString
    ) {
        logger.info("GET /api/session/" + dateString + "/" + timeString + "/seats");
        return sessionService
                .findSessionByDateAndTime(dateString, timeString)
                .getRoom()
                .getSeats()
                .stream()
                .map(SeatGet::new)
                .collect(Collectors.toList());
    }

    @GetMapping("/dates")
    public Set<Date> getSessionDates() {
        logger.info("GET /api/session/dates");
        return sessionService.getSessionDates();
    }

    @PostMapping("/create") public Map<String, Long>
    createNewSession(
            @RequestBody SessionCreate sessionDTO
    ) {
        logger.info("POST /api/session/create, session=" + sessionDTO);
        return sessionService.createNewSession(sessionDTO);
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

    @GetMapping("/generate-dates/{number}") public List<LocalDate>
    getDates(@PathVariable int number) {
        List<LocalDate> dateList = new ArrayList<>();
        LocalDate now = LocalDate.now();
        for (int i = 0; i < number; i++) {
            dateList.add(now);
            now = now.plusDays(1);
        }
        return dateList;
    }

}
