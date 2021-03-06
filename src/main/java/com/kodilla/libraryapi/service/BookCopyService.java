package com.kodilla.libraryapi.service;

import com.kodilla.libraryapi.domain.BookCopy;
import com.kodilla.libraryapi.exceptions.BookCopyNotFoundException;
import com.kodilla.libraryapi.repository.BookCopyRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
public class BookCopyService {
    private final BookCopyRepository bookCopyRepository;

    public BookCopy addBookCopy(final BookCopy bookCopy) {
        return bookCopyRepository.save(bookCopy);
    }

    public Long getNumberOfFreeCopiesByBookId(final long id) {
        return bookCopyRepository.countBookCopiesByBook_IdAndAvailableForRentIsTrue(id);
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
        return bookCopyRepository.findBookCopiesByBook_Id(id);
    }

    @Transactional
    public void setAsRented(final long id) {
        BookCopy copy = this.getBookCopyById(id);
        copy.setAvailableForRent(false);
    }

    @Transactional
    public void setAsReturned(final long id) {
        BookCopy copy = this.getBookCopyById(id);
        copy.setAvailableForRent(true);
    }

    public void deleteAllBookCopies() {
        bookCopyRepository.deleteAll();
    }


}
