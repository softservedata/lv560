package com.example.CinemaBoot.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SessionCreate {

    private long id;

    private long movieId;

    private long roomId;

}
