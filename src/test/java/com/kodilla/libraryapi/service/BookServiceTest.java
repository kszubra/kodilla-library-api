package com.kodilla.libraryapi.service;

import com.kodilla.libraryapi.domain.Book;
import com.kodilla.libraryapi.exceptions.book.BookAlreadyExistsException;
import com.kodilla.libraryapi.exceptions.book.BookNotFoundException;
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
public class BookServiceTest {
    @Autowired
    private BookService bookService;

    @Before
    public void cleanup() {
        bookService.deleteAllBooks();
    }

    @Test
    public void testGetBookById() {
        //Given
        Book testBook = new Book();
        testBook.setPublicationDate(LocalDate.now());
        testBook.setAuthor("Tolkien");
        testBook.setTitle("LOTR");

        //When
        bookService.addBook(testBook);
        long id = testBook.getId();
        Book receivedBook = bookService.getBookById(id);

        //Then
        Assert.assertEquals(testBook, receivedBook);
    }


    @Test(expected = BookNotFoundException.class)
    public void testGetBookByNonExistingId() {
        //Given & When
        long id = 1L;

        //Then
        Book receivedBook = bookService.getBookById(id);
    }

    @Test(expected = BookAlreadyExistsException.class)
    public void testAddingDuplicateBook() {
        //Given
        Book testBook1 = new Book();
        testBook1.setPublicationDate(LocalDate.now());
        testBook1.setAuthor("Tolkien");
        testBook1.setTitle("LOTR");
        Book testBook2 = new Book();
        testBook2.setPublicationDate(LocalDate.now());
        testBook2.setAuthor("Tolkien");
        testBook2.setTitle("LOTR");
        bookService.addBook(testBook1);
        bookService.addBook(testBook2);
    }

    @Test
    public void testGetBooksByNonExistingTitle() {
        //Given & When
        String title = "Example";

        //When
        List<Book> receivedBook = bookService.getBooksByTitle(title);

        //Then
        Assert.assertTrue(receivedBook.isEmpty());
    }

    @Test
    public void testGetAllBooks() {
        //Given
        Book testBook1 = new Book();
        testBook1.setPublicationDate(LocalDate.now());
        testBook1.setAuthor("Tolkien");
        testBook1.setTitle("LOTR");
        Book testBook2 = new Book();
        testBook2.setPublicationDate(LocalDate.now());
        testBook2.setAuthor("Tolkien");
        testBook2.setTitle("Silmarillion");
        bookService.addBook(testBook1);
        bookService.addBook(testBook2);

        //When
        List<Book> receivedBooks = bookService.getAllBooks();

        //Then
        Assert.assertEquals(testBook1, receivedBooks.get(0));
        Assert.assertEquals(testBook2, receivedBooks.get(1));
    }

    @Test
    public void testDeleteBookById() {
        //Given
        Book testBook1 = new Book();
        testBook1.setPublicationDate(LocalDate.now());
        testBook1.setAuthor("Tolkien");
        testBook1.setTitle("LOTR");
        Book testBook2 = new Book();
        testBook2.setPublicationDate(LocalDate.now());
        testBook2.setAuthor("Tolkien");
        testBook2.setTitle("Silmarillion");
        bookService.addBook(testBook1);
        bookService.addBook(testBook2);

        //When
        long id = testBook2.getId();
        bookService.deleteBookById(id);
        List<Book> books = bookService.getAllBooks();

        //Then
        Assert.assertEquals(1, books.size());
        Assert.assertFalse(books.contains(testBook2));
    }

    @Test
    public void testGetBookByTitle() {
        //Given
        Book testBook1 = new Book();
        testBook1.setPublicationDate(LocalDate.of(1975, 03, 12));
        testBook1.setAuthor("Tolkien");
        testBook1.setTitle("LOTR");
        Book testBook2 = new Book();
        testBook2.setPublicationDate(LocalDate.of(1980, 05, 05));
        testBook2.setAuthor("Tolkien");
        testBook2.setTitle("LOTR");
        Book testBook3 = new Book();
        testBook3.setPublicationDate(LocalDate.of(2015, 12, 03));
        testBook3.setAuthor("Random Author");
        testBook3.setTitle("Some title");
        bookService.addBook(testBook1);
        bookService.addBook(testBook2);
        bookService.addBook(testBook3);

        //When
        List<Book> books = bookService.getBooksByTitle("LOTR");

        //Then
        Assert.assertEquals(2, books.size());
        Assert.assertTrue(books.contains(testBook1));
        Assert.assertTrue(books.contains(testBook2));
        Assert.assertFalse(books.contains(testBook3));
    }



    @Test
    public void testGetBooksAmount() {
        //Given
        Book testBook1 = new Book();
        testBook1.setPublicationDate(LocalDate.of(1975, 03, 12));
        testBook1.setAuthor("Tolkien");
        testBook1.setTitle("LOTR");
        Book testBook2 = new Book();
        testBook2.setPublicationDate(LocalDate.of(1980, 05, 05));
        testBook2.setAuthor("Tolkien");
        testBook2.setTitle("LOTR");
        Book testBook3 = new Book();
        testBook3.setPublicationDate(LocalDate.of(2015, 12, 03));
        testBook3.setAuthor("Random Author");
        testBook3.setTitle("Some title");
        bookService.addBook(testBook1);
        bookService.addBook(testBook2);
        bookService.addBook(testBook3);

        //When
        long result = bookService.getBooksAmount();

        //Then
        Assert.assertEquals(3, result);
    }
}