package com.example.CinemaBoot.dto.session;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SessionCreate {

    private long movieId;

    private long roomId;

}
