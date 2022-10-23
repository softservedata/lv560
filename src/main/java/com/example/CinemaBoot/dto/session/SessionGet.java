package com.example.CinemaBoot.dto.session;

import com.example.CinemaBoot.dto.movie.MovieGet;
import com.example.CinemaBoot.dto.room.RoomGet;
import com.example.CinemaBoot.models.Session;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SessionGet {

    private long id;

    private MovieGet movie;

    private RoomGet room;

    private Date date;

    private Date time;

    public SessionGet(Session session) {
        this.id = session.getId();
        this.movie = new MovieGet(session.getMovie());
        this.room = new RoomGet(session.getRoom());
        this.date = session.getDate();
        this.time = session.getTime();
    }

}
