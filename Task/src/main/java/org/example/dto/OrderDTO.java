package org.example.dto;

import org.example.model.Room;
import java.time.LocalDate;

public class OrderDTO {

    private final Room room;

    private final LocalDate arrivalTime;

    private final LocalDate departureTime;

    public OrderDTO(Room room, LocalDate arrivalTime, LocalDate departureTime) {
        this.room = room;
        this.arrivalTime = arrivalTime;
        this.departureTime = departureTime;
    }

    public Room getRoom() {
        return room;
    }

    public LocalDate getArrivalTime() {
        return arrivalTime;
    }

    public LocalDate getDepartureTime() {
        return departureTime;
    }
}