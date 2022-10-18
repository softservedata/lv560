package com.example.CinemaBoot;

import com.example.CinemaBoot.models.*;
import com.example.CinemaBoot.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.sql.Time;
import java.util.HashSet;
import java.util.Set;

@Component
public class CMDRunner implements CommandLineRunner {

    @Autowired
    UserRepository userRepository;

    @Autowired
    SessionRepository sessionRepository;

    @Autowired
    MovieRepository movieRepository;

    @Autowired
    BookRepository bookRepository;

    @Autowired
    RoomRepository roomRepository;

    @Autowired
    SeatRepository seatRepository;

    @Override
    public void run(String... args) {
//        User user = new User();
//        user.setEmail("user@mail.com");
//        user.setPassword("password");
//        user.setAdmin(false);
//        userRepository.save(user);
//        System.out.println("Save user" + user);
//
//        Movie avengers = new Movie();
//        avengers.setName("Avengers");
//        avengers.setGenre("action");
//        movieRepository.save(avengers);
//
//        Session session = new Session();
//        session.setDate(new Date(1800));
//        session.setTime(new Time(1800));
//        session.setMovie(avengers);
//        sessionRepository.save(session);
//
//        Room saturn = new Room();
//        saturn.setName("Saturn");

    }

}
