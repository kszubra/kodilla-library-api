package com.kodilla.libraryapi.controller;

import com.kodilla.libraryapi.domain.BookCopy;
import com.kodilla.libraryapi.domain.dto.BookCopyDto;
import com.kodilla.libraryapi.mapper.BookCopyMapper;
import com.kodilla.libraryapi.service.BookCopyService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/library")
@AllArgsConstructor
public class BookCopyController {
    private final BookCopyService bookCopyService;
    private final BookCopyMapper bookCopyMapper;

    @PostMapping("copies")
    public void addBookCopy(@RequestBody BookCopyDto dto) {
        bookCopyService.addBookCopy(bookCopyMapper.mapToBookCopy(dto));
    }

    @PutMapping("copies")
    @Transactional
    public void updateBookCopyStatus(@RequestParam("id") long id, @RequestParam("status") String status) {
        BookCopy copy = bookCopyService.getBookCopyById(id);
        copy.setStatusByString(status);
    }

    @GetMapping("copies/{id}")
    public long getNumberOfFreeCopies(@PathVariable("id") long bookId) {
        return bookCopyService.getNumberOfFreeCopiesByBookId(bookId);
    }
}
