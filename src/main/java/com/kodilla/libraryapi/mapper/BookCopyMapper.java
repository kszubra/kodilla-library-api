package com.kodilla.libraryapi.mapper;

import com.kodilla.libraryapi.domain.BookCopy;
import com.kodilla.libraryapi.domain.dto.BookCopyDto;
import com.kodilla.libraryapi.enumerics.BookCopyStatus;
import com.kodilla.libraryapi.service.BookService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class BookCopyMapper {
    private final BookService bookService;
    private static final Logger LOGGER = LoggerFactory.getLogger(BookCopyMapper.class);

    public BookCopy mapToBookCopy(BookCopyDto dto) {
        LOGGER.info(dto.toString());

        return BookCopy.builder()
                .book( bookService.getBookById( dto.getBookId() ) )
                .availableForRent( dto.isAvailableForRent() )
                .status( BookCopyStatus.valueOf(dto.getStatus()) )
                .rents( dto.getRents() )
                .build();
    }
}
