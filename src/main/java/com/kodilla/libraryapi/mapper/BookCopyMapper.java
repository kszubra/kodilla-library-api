package com.kodilla.libraryapi.mapper;

import com.kodilla.libraryapi.domain.BookCopy;
import com.kodilla.libraryapi.domain.dto.BookCopyDto;
import com.kodilla.libraryapi.enumerics.BookCopyStatus;
import com.kodilla.libraryapi.service.BookService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
@Slf4j
public class BookCopyMapper {
    private final BookService bookService;

    public BookCopy mapToBookCopy(BookCopyDto dto) {
        log.info(dto.toString());

        return BookCopy.builder()
                .book( bookService.getBookById( dto.getBookId() ) )
                .availableForRent( dto.isAvailableForRent() )
                .status( BookCopyStatus.valueOf(dto.getStatus()) )
                .rents( dto.getRents() )
                .build();
    }
}
