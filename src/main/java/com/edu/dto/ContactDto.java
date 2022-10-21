package com.edu.dto;

import lombok.*;

import javax.validation.constraints.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@With
public class ContactDto {

    //@NotNull(message = "ID cannot be null")
    private Integer id;

    @Size(min = 2, max = 150, message = "firstname should be between 2 and 150 chars")
    @NotBlank(message = "firstname cannot be empty")
    private String firstname;

    private String lastname;

    @NotBlank(message = "email cannot be empty")
    private String email;

    private String telephone;
}
