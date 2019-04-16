package com.kodilla.libraryapi.controller;

import com.kodilla.libraryapi.domain.dto.BookDto;
import com.kodilla.libraryapi.mapper.BookMapper;
import com.kodilla.libraryapi.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
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

    @PostMapping("addBook")
    public void addBook(@RequestBody BookDto dto) {
        bookService.addBook(bookMapper.mapToBook(dto));
    }
}
