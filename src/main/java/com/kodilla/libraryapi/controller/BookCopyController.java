package com.kodilla.libraryapi.controller;

import com.kodilla.libraryapi.domain.BookCopy;
import com.kodilla.libraryapi.domain.dto.BookCopyDto;
import com.kodilla.libraryapi.mapper.BookCopyMapper;
import com.kodilla.libraryapi.service.BookCopyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
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

    @PostMapping("addBookCopy")
    public void addBookCopy(@RequestBody BookCopyDto dto) {
        bookCopyService.addBookCopy(bookCopyMapper.mapToBookCopy(dto));
    }

    @PutMapping("updateBookCopy")
    @Transactional
    public void updateBookCopyStatus(@RequestParam("id") long id, @RequestParam("status") String status) {
        BookCopy copy = bookCopyService.getBookCopyById(id);
        copy.setStatusByString(status);
    }

    @GetMapping("freeCopies")
    public long getNumberOfFreeCopies(@RequestParam("bookId") long bookId) {
        return bookCopyService.getNumberOfFreeCopiesByBookId(bookId);
    }
}
