package com.kodilla.libraryapi.service;

import com.kodilla.libraryapi.domain.Book;
import com.kodilla.libraryapi.exceptions.book.BookAlreadyExistsException;
import com.kodilla.libraryapi.exceptions.book.BookNotFoundException;
import com.kodilla.libraryapi.repository.BookRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class BookService {
    private final BookRepository bookRepository;

    public Book addBook(final Book book) {
        if(bookRepository.existsByAuthorAndTitleAndPublicationDate(
                book.getAuthor(),
                book.getTitle(),
                book.getPublicationDate()
        )) {
            throw new BookAlreadyExistsException();
        }
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

    public List<Book> getBooksByTitle(final String title) {
        return bookRepository.getBooksByTitle(title);
    }

    public long getBooksAmount() {
        return bookRepository.count();
    }

    public void deleteAllBooks() {
        bookRepository.deleteAll();
    }


}
