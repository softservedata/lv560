package com.example.CinemaBoot.controllers;

import com.example.CinemaBoot.exceptions.SessionBadRequestException;
import com.example.CinemaBoot.exceptions.SessionNotFoundException;
import com.example.CinemaBoot.models.Book;
import com.example.CinemaBoot.models.Seat;
import com.example.CinemaBoot.models.Session;
import com.example.CinemaBoot.services.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@RestController
@RequestMapping("/api/session")
public class SessionController {

    @Autowired
    SessionService sessionService;

    @RequestMapping("/{dateString}")
    public List<Session> getSessionsByDate(@PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) String dateString) {
        List<Session> sessions = sessionService.getAllSessionsByDate(parseDate(dateString));
        setOccupiedSeats(sessions);
        return sessions;
    }

    @RequestMapping("/{dateString}/{timeString}")
    public Session getSessionByDateAndTime(
            @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) String dateString,
                                 @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.TIME) String timeString
    ) {
        Optional<Session> session = sessionService.getSessionByDateAndTime(parseDate(dateString), parseTime(timeString));
        if (session.isEmpty()) {
            throw new SessionNotFoundException("No session for date:" + dateString + ", time:" + timeString);
        }
        setOccupiedSeats(session.get());
        return session.get();
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

    private void setOccupiedSeats(List<Session> sessions) {
        for (Session session : sessions) {
            setOccupiedSeats(session);
        }
    }

    private void setOccupiedSeats(Session session) {
        for (Book book : session.getBooks()) {
            for (Seat seat : book.getSeats()) {
                seat.setOccupied(true);
            }
        }
        return;
    }
}
