package com.kodilla.libraryapi.mapper;

import com.kodilla.libraryapi.domain.BookCopy;
import com.kodilla.libraryapi.domain.dto.BookCopyDto;
import com.kodilla.libraryapi.service.BookService;
import org.springframework.stereotype.Component;

@Component
public class BookCopyMapper {
    private final BookService bookService;

    public BookCopyMapper(final BookService bookService) {
        this.bookService = bookService;
    }

    public BookCopy mapToBookCopy(BookCopyDto dto) {
        return BookCopy.builder()
                .book( bookService.getBookById( dto.getBookId() ) )
                .availableForRent( dto.isAvailableForRent() )
                .status( dto.getStatus() )
                .rents( dto.getRents() )
                .build();
    }
}
