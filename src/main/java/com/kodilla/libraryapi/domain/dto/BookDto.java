package com.kodilla.libraryapi.domain.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Builder(toBuilder = true)
public class BookDto {

    private long id;
    private String title;
    private String author;
    private LocalDate publicationDate;
}
