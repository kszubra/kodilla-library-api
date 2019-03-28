package com.kodilla.libraryapi.service;

import com.kodilla.libraryapi.domain.Book;
import com.kodilla.libraryapi.domain.BookCopy;
import com.kodilla.libraryapi.domain.Rent;
import com.kodilla.libraryapi.domain.User;
import com.kodilla.libraryapi.enumerics.BookCopyStatus;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RentServiceTest {
    @Autowired
    private RentService rentService;
    @Autowired
    private UserService userService;
    @Autowired
    private BookService bookService;
    @Autowired
    private BookCopyService bookCopyService;

    private User testUser;
    private Book testBook;
    private BookCopy testBookCopy;
    private Rent testRent;

    private void prepareTestObjects() {
        testUser = new User();
        testBook = new Book();
        testBookCopy = new BookCopy();
        testRent = new Rent();

        testBook.setAuthor("Tolkien");
        testBook.setTitle("LOTR");
        testBook.setPublicationDate(LocalDate.of(1973, 12, 8));
        bookService.addBook(testBook);

        testUser.setName("John");
        testUser.setSurname("Rambo");
        testUser.setHasAdminRights(false);
        testUser.setPrefferedCurrency("PLN");
        testUser.setRegistrationDate(LocalDate.now());
        userService.addUser(testUser);

        testBookCopy.setBook(testBook);
        testBookCopy.setAvailableForRent(true);
        testBookCopy.setStatus("in use");
        bookCopyService.addBookCopy(testBookCopy);

        testRent.setBookCopy(testBookCopy);
        testRent.setUser(testUser);
        testRent.setRentDate(LocalDate.now());
        testRent.setReturnDeadline(LocalDate.now().plusDays(30));
    }

    @Test
    public void testGetRentById() {
        //Given
        prepareTestObjects();

        //When
        rentService.addRent(testRent);
        long rentId = testRent.getId();
        Rent receivedRent = rentService.getRentById(rentId);

        //Then
        Assert.assertEquals(testRent, receivedRent);
    }

    @Test
    public void testGetAllRents() {
        //Given

        //When

        //Then

    }

    @Test
    public void testSetRentAsReturned() {
        //Given

        //When

        //Then
    }
}