package com.example.CinemaBoot.services;

import com.example.CinemaBoot.exceptions.SessionBadRequestException;
import com.example.CinemaBoot.models.Session;
import com.example.CinemaBoot.repositories.SessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class SessionService {

    @Autowired
    SessionRepository sessionRepository;

    public List<Session> getAllSessionsByDate(Date date) {
        return sessionRepository.findAllByDate(date);
    }

    public Optional<Session> getSessionByDateAndTime(Date date, Date time) {
        return sessionRepository.findByDateAndTime(date, time);
    }

}
