package com.example.CinemaBoot.dto.room;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoomCreate {

    private String name;

    private int numberOfSeats;

}
