package com.kodilla.libraryapi.service;

import com.kodilla.libraryapi.domain.Book;
import com.kodilla.libraryapi.exceptions.BookNotFoundException;
import com.kodilla.libraryapi.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {
    @Autowired
    private BookRepository bookRepository;

    public Book addBook(final Book book) {
        return bookRepository.save(book);
    }

    public Book getBookById(final long id) {
        return bookRepository.findById(id).orElseThrow(BookNotFoundException::new);
    }

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public void deleteBookById(final long id) {
        bookRepository.deleteById(id);
    }

    public Book getBookByTitle(final String title) {
        return bookRepository.getBookByTitle(title).orElseThrow(BookNotFoundException::new);
    }

    public long getBooksAmount() {
        return bookRepository.count();
    }


}
