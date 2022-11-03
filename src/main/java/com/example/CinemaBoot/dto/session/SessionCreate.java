package com.example.CinemaBoot.dto.session;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SessionCreate {

    private long movieId;

    private long roomId;

    //TODO:VALIDATE
    private String date;

    private String time;

}
