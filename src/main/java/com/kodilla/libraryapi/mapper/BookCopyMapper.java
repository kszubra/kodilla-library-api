package com.kodilla.libraryapi.mapper;

import com.kodilla.libraryapi.domain.BookCopy;
import com.kodilla.libraryapi.domain.dto.BookCopyDto;
import org.springframework.stereotype.Component;

@Component
public class BookCopyMapper {

    public BookCopy mapToBookCopy(BookCopyDto dto) {
        return BookCopy.builder()
                .book( dto.getBook() )
                .availableForRent( dto.isAvailableForRent() )
                .status( dto.getStatus() )
                .rents( dto.getRents() )
                .build();
    }
}
