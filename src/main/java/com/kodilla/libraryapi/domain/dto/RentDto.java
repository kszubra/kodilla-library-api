package com.kodilla.libraryapi.domain.dto;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class RentDto {

    private long id;
    private long userId;
    private long bookCopyId;
    private LocalDate rentDate;
    private LocalDate returnDeadline;
    private long fineId;
    private boolean returned;
    private String rentCode;
}
