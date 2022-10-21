package com.example.CinemaBoot.services;

import com.example.CinemaBoot.dto.BookCreate;
import com.example.CinemaBoot.exceptions.SeatIsOccupiedException;
import com.example.CinemaBoot.models.Book;
import com.example.CinemaBoot.models.Seat;
import com.example.CinemaBoot.models.Session;
import com.example.CinemaBoot.repositories.BookRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class BookService {

    Logger logger = LoggerFactory.getLogger(BookService.class);

    @Autowired
    BookRepository bookRepository;

    @Autowired
    SessionService sessionService;

    @Autowired
    UserService userService;

    @Autowired
    SeatService seatService;

    @Transactional
    public Map<String, Long> createNewBooking(String dateString, String timeString, BookCreate bookDTO) {
        Book book = new Book();
        Session session = sessionService.getSessionByDateAndTime(dateString, timeString);
        book.setSession(session);
        book.setUser(userService.findById(bookDTO.getUserId()));

        List<Seat> seats = new ArrayList<>();
        bookDTO.getSeats().forEach(seatNumber -> seats.add(seatService.getByRoomIdAndNumber(bookDTO.getRoomId(), seatNumber)));
        long occupiedSeatsSize = seats
                .stream()
                .filter(session.getOccupiedSeats()::contains)
                .count();
        if (occupiedSeatsSize > 0) {
            logger.info("Number of occupied seats=" + occupiedSeatsSize);
            throw new SeatIsOccupiedException("One of the seats you are trying to book is occupied");
        }

        book.setSeats(seats);
        long id = bookRepository
                .save(book)
                .getId();
        return Map.of("id", id);
    }

}
