package com.kodilla.libraryapi.service;

import com.kodilla.libraryapi.domain.Book;
import com.kodilla.libraryapi.domain.BookCopy;
import com.kodilla.libraryapi.enumerics.BookCopyStatus;
import com.kodilla.libraryapi.exceptions.BookCopyNotFoundException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BookCopyServiceTest {
    @Autowired
    private BookCopyService bookCopyService;
    @Autowired
    private BookService bookService;

    @Before
    public void cleanup() {
        bookCopyService.deleteAllBookCopies();
        bookService.deleteAllBooks();
    }

    @Test
    public void testGetBookCopyById() {
        //Given
        Book testBook = new Book();
        testBook.setPublicationDate(LocalDate.now());
        testBook.setAuthor("Tolkien");
        testBook.setTitle("LOTR");
        bookService.addBook(testBook);

        BookCopy lotrCopyOne = new BookCopy();
        lotrCopyOne.setBook(testBook);
        lotrCopyOne.setAvailableForRent(true);
        lotrCopyOne.setStatus(BookCopyStatus.IN_USE);
        bookCopyService.addBookCopy(lotrCopyOne);

        //When
        long id = lotrCopyOne.getId();
        BookCopy receivedCopy = bookCopyService.getBookCopyById(id);

        //Then
        Assert.assertEquals(lotrCopyOne, receivedCopy);
        Assert.assertEquals("Tolkien", receivedCopy.getBook().getAuthor());
        Assert.assertEquals("LOTR", receivedCopy.getBook().getTitle());
    }

    @Test
    public void testGetCopiesByBookId() {
        //Given
        Book testBook = new Book();
        testBook.setPublicationDate(LocalDate.now());
        testBook.setAuthor("Tolkien");
        testBook.setTitle("LOTR");
        bookService.addBook(testBook);
        long bookId = testBook.getId();

        BookCopy lotrCopyOne = new BookCopy();
        lotrCopyOne.setBook(testBook);
        lotrCopyOne.setAvailableForRent(true);
        lotrCopyOne.setStatus(BookCopyStatus.IN_USE);
        bookCopyService.addBookCopy(lotrCopyOne);
        BookCopy lotrCopyTwo = new BookCopy();
        lotrCopyTwo.setBook(testBook);
        lotrCopyTwo.setAvailableForRent(false);
        lotrCopyTwo.setStatus(BookCopyStatus.LOST);
        bookCopyService.addBookCopy(lotrCopyTwo);

        //When
        List<BookCopy> copies = bookCopyService.getCopiesByBookId(bookId);

        //Then
        Assert.assertEquals(2, copies.size());
        Assert.assertEquals("IN USE", copies.get(0).getStatus().toString());
        Assert.assertEquals("LOST", copies.get(1).getStatus().toString());
        Assert.assertEquals(true, copies.get(0).isAvailableForRent());
        Assert.assertEquals(false, copies.get(1).isAvailableForRent());
        Assert.assertEquals("Tolkien", copies.get(0).getBook().getAuthor());
        Assert.assertEquals("LOTR", copies.get(1).getBook().getTitle());
        Assert.assertEquals("Tolkien", copies.get(0).getBook().getAuthor());
        Assert.assertEquals("LOTR", copies.get(1).getBook().getTitle());
    }

    @Test(expected = BookCopyNotFoundException.class)
    public void testGetCopyByNonExistingId() {
        //Given & When
        long id = 1L;

        //Then
        bookCopyService.getBookCopyById(id);
    }

    @Test
    public void testGetCopiesByNonExistingBookId() {
        //Given & When
        long id = 1L;
        List<BookCopy> copies = bookCopyService.getCopiesByBookId(id);

        //Then
        Assert.assertTrue(copies.isEmpty());
    }

    @Test
    public void testSettingCopyAsRented() {
        //Given
        Book testBook = new Book();
        testBook.setPublicationDate(LocalDate.now());
        testBook.setAuthor("Tolkien");
        testBook.setTitle("LOTR");
        bookService.addBook(testBook);

        BookCopy lotrCopyOne = new BookCopy();
        lotrCopyOne.setBook(testBook);
        lotrCopyOne.setAvailableForRent(true);
        lotrCopyOne.setStatus(BookCopyStatus.IN_USE);
        bookCopyService.addBookCopy(lotrCopyOne);

        // & When
        long id = lotrCopyOne.getId();
        bookCopyService.setAsRented(id);
        BookCopy received = bookCopyService.getBookCopyById(id);

        //Then
        Assert.assertFalse(received.isAvailableForRent());
    }

    @Test
    public void testSettingCopyAsReturned() {
        //Given
        Book testBook = new Book();
        testBook.setPublicationDate(LocalDate.now());
        testBook.setAuthor("Tolkien");
        testBook.setTitle("LOTR");
        bookService.addBook(testBook);

        BookCopy lotrCopyOne = new BookCopy();
        lotrCopyOne.setBook(testBook);
        lotrCopyOne.setAvailableForRent(false);
        lotrCopyOne.setStatus(BookCopyStatus.IN_USE);
        bookCopyService.addBookCopy(lotrCopyOne);

        // & When
        long id = lotrCopyOne.getId();
        bookCopyService.setAsReturned(id);
        BookCopy received = bookCopyService.getBookCopyById(id);

        //Then
        Assert.assertTrue(received.isAvailableForRent());
    }

}