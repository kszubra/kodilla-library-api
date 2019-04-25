package com.kodilla.libraryapi.domain.dto;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class UserDto {

    private long id;
    private String name;
    private String surname;
    private LocalDate registrationDate;
    private String prefferedCurrency;
    private boolean hasAdminRights;
    private String emailAddress;
}
