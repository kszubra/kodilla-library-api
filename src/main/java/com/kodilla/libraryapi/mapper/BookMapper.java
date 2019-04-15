package com.kodilla.libraryapi.mapper;

import com.kodilla.libraryapi.domain.Book;
import com.kodilla.libraryapi.domain.dto.BookDto;
import org.springframework.stereotype.Component;

@Component
public class BookMapper {

    public Book mapToBook(BookDto dto) {
        return Book.builder()
                .title( dto.getTitle() )
                .author( dto.getTitle() )
                .publicationDate( dto.getPublicationDate() )
                .build();
    }
}
