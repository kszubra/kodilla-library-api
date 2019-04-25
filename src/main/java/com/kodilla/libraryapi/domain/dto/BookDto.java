package com.kodilla.libraryapi.domain.dto;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class BookDto {
    private long id;
    private String title;
    private String author;
    private LocalDate publicationDate;
}
