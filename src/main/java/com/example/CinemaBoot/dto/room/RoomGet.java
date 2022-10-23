package com.example.CinemaBoot.dto.room;

import com.example.CinemaBoot.models.Room;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoomGet {

    private long id;

    private String name;

    public RoomGet(Room room) {
        this.id = room.getId();
        this.name = room.getName();
    }

}
