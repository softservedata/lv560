package com.example.CinemaBoot.dto.seat;

import com.example.CinemaBoot.models.Seat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SeatGet {

    private long id;

    private int number;

    private boolean isOccupied;

    public SeatGet(Seat seat) {
        this.id = seat.getId();
        this.number = seat.getNumber();
        this.isOccupied = seat.isOccupied();
    }

}
