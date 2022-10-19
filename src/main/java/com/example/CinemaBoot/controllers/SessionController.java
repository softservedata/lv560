package com.example.CinemaBoot.controllers;

import com.example.CinemaBoot.exceptions.SessionBadRequestException;
import com.example.CinemaBoot.exceptions.SessionNotFoundException;
import com.example.CinemaBoot.models.Book;
import com.example.CinemaBoot.models.Seat;
import com.example.CinemaBoot.models.Session;
import com.example.CinemaBoot.services.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@RestController
@RequestMapping("/api/session")
public class SessionController {

    @Autowired
    SessionService sessionService;

    @GetMapping("/{dateString}")
    public List<Session> getSessionsByDate(@PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) String dateString) {
        List<Session> sessions = sessionService.getAllSessionsByDate(parseDate(dateString));
        SessionService.setOccupiedSeats(sessions);
        return sessions;
    }

    @GetMapping("/{dateString}/{timeString}")
    public Session getSessionByDateAndTime(
            @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) String dateString,
                                 @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.TIME) String timeString
    ) {
        Optional<Session> session = sessionService.getSessionByDateAndTime(parseDate(dateString), parseTime(timeString));
        if (session.isEmpty()) {
            throw new SessionNotFoundException("No session for date:" + dateString + ", time:" + timeString);
        }
        session.get().setOccupiedSeats();
        return session.get();
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
        return sessionService.getAllDistinctByDate();
    }

    @PostMapping("/{dateString}/{timeString}/new")
    public Map<String, Long> createNewSession(
            @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) String dateString,
            @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.TIME) String timeString,
            @RequestBody Session session
    ) {
        session.setDate(parseDate(dateString));
        session.setTime(parseTime(timeString));
        long id = sessionService
                .save(session)
                .getId();
        return Map.of("id", id);
    }

    private Date parseDate(String dateString) {
        try {
            return new SimpleDateFormat("yyyy-MM-dd").parse(dateString);
        } catch (ParseException e) {
            throw new SessionBadRequestException("Improper date format:" + dateString + ", use yyyy-MM-dd");
        }
    }

    private Date parseTime(String timeString) {
        try {
            return new SimpleDateFormat("hh:mm").parse(timeString);
        } catch (ParseException e) {
            throw new SessionBadRequestException("Improper date format:" + timeString + ", use hh:mm");
        }
    }

}
