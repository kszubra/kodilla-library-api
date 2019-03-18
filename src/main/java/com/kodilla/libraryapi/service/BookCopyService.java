package com.kodilla.libraryapi.service;

import com.kodilla.libraryapi.domain.BookCopy;
import com.kodilla.libraryapi.exceptions.BookCopyNotFoundException;
import com.kodilla.libraryapi.repository.BookCopyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookCopyService {
    @Autowired
    private BookCopyRepository bookCopyRepository;

    public BookCopy addBookCopy(final BookCopy bookCopy) {
        return bookCopyRepository.save(bookCopy);
    }

    public BookCopy getBookCopyById(final long id) {
        return bookCopyRepository.findById(id).orElseThrow(BookCopyNotFoundException::new);
    }

    /***
     * Returns a list of copies of a book with provided book id
     *
     * @param id
     * @return
     */
    public List<BookCopy> getCopiesByBookId(final long id) {
        return bookCopyRepository.findAll().stream()
                .filter(e -> e.getBook().getId() == id)
                .collect(Collectors.toList());
    }


}
