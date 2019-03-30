package com.kodilla.libraryapi.controller;

import com.kodilla.libraryapi.mapper.BookCopyMapper;
import com.kodilla.libraryapi.service.BookCopyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/bookCopies")
public class BookCopyController {
    private final BookCopyService bookCopyService;
    private final BookCopyMapper bookCopyMapper;

    @Autowired
    public BookCopyController(BookCopyService bookCopyService, BookCopyMapper bookCopyMapper) {
        this.bookCopyService = bookCopyService;
        this.bookCopyMapper = bookCopyMapper;
    }
}
