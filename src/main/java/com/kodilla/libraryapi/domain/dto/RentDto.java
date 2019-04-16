package com.kodilla.libraryapi.domain.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class RentDto {

    private long id;
    private long userId;
    private long bookCopyId;
    private LocalDate rentDate;
    private LocalDate returnDeadline;
    private long fineId;
    private boolean isReturned;
    private String rentCode;
}
