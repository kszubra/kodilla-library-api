package com.kodilla.libraryapi.controller;

import com.kodilla.libraryapi.mapper.BookMapper;
import com.kodilla.libraryapi.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/books")
public class BookController {
    private final BookMapper bookMapper;
    private final BookService bookService;

    @Autowired
    public BookController(BookMapper mapper, BookService service) {
        this.bookMapper = mapper;
        this.bookService = service;
    }
}
