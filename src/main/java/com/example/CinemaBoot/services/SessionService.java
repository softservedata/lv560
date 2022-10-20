package com.example.CinemaBoot.services;

import com.example.CinemaBoot.dto.BookCreate;
import com.example.CinemaBoot.exceptions.SessionBadRequestException;
import com.example.CinemaBoot.exceptions.SessionNotFoundException;
import com.example.CinemaBoot.models.Book;
import com.example.CinemaBoot.models.Seat;
import com.example.CinemaBoot.models.Session;
import com.example.CinemaBoot.repositories.SessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class SessionService {

    @Autowired
    SessionRepository sessionRepository;

    public List<Session> getSessionsByDate(String dateString) {
        List<Session> sessions = sessionRepository.findAllByDate(parseDate(dateString));
        SessionService.setOccupiedSeats(sessions);
        return sessions;
    }

    public Session getSessionByDateAndTime(String dateString, String timeString) {
        Optional<Session> session = sessionRepository.findByDateAndTime(parseDate(dateString), parseTime(timeString));
        if (session.isEmpty()) {
            throw new SessionNotFoundException("No session for date:" + dateString + ", time:" + timeString);
        }
        session.get().setOccupiedSeats();
        return session.get();
    }

    public Set<Date> getSessionDates() {
        List<Session> allSessions = sessionRepository.findAll();
        Set<Date> distinctDates = new HashSet<>();
        allSessions.forEach(session -> distinctDates.add(session.getDate()));
        return distinctDates;
    }

    public static void setOccupiedSeats(List<Session> sessions) {
        for (Session session : sessions) {
            session.setOccupiedSeats();
        }
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
