package com.kodilla.libraryapi.domain.dto;

import com.kodilla.libraryapi.domain.Rent;
import com.kodilla.libraryapi.enumerics.BookCopyStatus;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder(toBuilder = true)
public class BookCopyDto {

    private long id;
    private long bookId;
    private BookCopyStatus status;
    private boolean isAvailableForRent;
    private List<Rent> rents;
}
