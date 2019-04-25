package com.kodilla.libraryapi.controller;

import com.kodilla.libraryapi.domain.dto.BookDto;
import com.kodilla.libraryapi.mapper.BookMapper;
import com.kodilla.libraryapi.service.BookService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping
@AllArgsConstructor
public class BookController {
    private final BookMapper bookMapper;
    private final BookService bookService;

    @PostMapping("books")
    public void addBook(@RequestBody BookDto dto) {
        bookService.addBook(bookMapper.mapToBook(dto));
    }
}
