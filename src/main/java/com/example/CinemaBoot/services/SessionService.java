package com.example.CinemaBoot.services;

import com.example.CinemaBoot.dto.session.SessionCreate;
import com.example.CinemaBoot.dto.session.SessionGet;
import com.example.CinemaBoot.exceptions.BadRequestException;
import com.example.CinemaBoot.exceptions.NotFoundException;
import com.example.CinemaBoot.models.Session;
import com.example.CinemaBoot.repositories.SessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class SessionService {

    @Autowired
    SessionRepository sessionRepository;

    @Autowired
    MovieService movieService;

    @Autowired
    RoomService roomService;

    @Transactional(readOnly = true)
    public List<SessionGet> getSessionsByDate(String dateString) {
        List<Session> sessions = sessionRepository.findAllByDate(parseDate(dateString));
        SessionService.setOccupiedSeats(sessions);
        return sessions
                .stream()
                .map(SessionGet::new)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public SessionGet getSessionByDateAndTime(String dateString, String timeString) {
        return new SessionGet(findSessionByDateAndTime(dateString, timeString));
    }

    @Transactional(readOnly = true)
    public Set<Date> getSessionDates() {
        List<Session> allSessions = sessionRepository.findAll();
        Set<Date> distinctDates = new HashSet<>();
        allSessions.forEach(session -> distinctDates.add(session.getDate()));
        return distinctDates;
    }

    @Transactional(readOnly = true)
    public Session findSessionByDateAndTime(String dateString, String timeString) {
        Optional<Session> session = sessionRepository.findByDateAndTime(parseDate(dateString), parseTime(timeString));
        if (session.isEmpty()) {
            throw new NotFoundException("No session for date:" + dateString + ", time:" + timeString);
        }
        session.get().setOccupiedSeats();
        return session.get();
    }

    @Transactional(readOnly = true)
    public boolean existsByDateAndTime(String dateString, String timeString) {
        return sessionRepository.existsByDateAndTime(parseDate(dateString), parseTime(timeString));
    }

    @Transactional
    public Map<String, Long> createNewSession(String dateString, String timeString, SessionCreate sessionDTO) {
        if (existsByDateAndTime(dateString, timeString)) {
            throw new BadRequestException("Session with date=" + dateString + " and time=" + timeString + " already exists");
        }
        Session session = new Session();
        session.setDate(parseDate(dateString));
        session.setTime(parseTime(timeString));
        session.setMovie(movieService.findById(sessionDTO.getMovieId()));
        session.setRoom(roomService.findById(sessionDTO.getRoomId()));

        long id = sessionRepository
                .save(session)
                .getId();
        return Map.of("id", id);
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
            throw new BadRequestException("Improper date format:" + dateString + ", use yyyy-MM-dd");
        }
    }

    private Date parseTime(String timeString) {
        try {
            return new SimpleDateFormat("hh:mm").parse(timeString);
        } catch (ParseException e) {
            throw new BadRequestException("Improper date format:" + timeString + ", use hh:mm");
        }
    }

}
