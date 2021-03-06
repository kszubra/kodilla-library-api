package com.kodilla.libraryapi.service;

import com.kodilla.libraryapi.domain.Book;
import com.kodilla.libraryapi.domain.BookCopy;
import com.kodilla.libraryapi.domain.Rent;
import com.kodilla.libraryapi.domain.User;
import com.kodilla.libraryapi.enumerics.BookCopyStatus;
import com.kodilla.libraryapi.exceptions.rent.CopyAlreadyRentedException;
import com.kodilla.libraryapi.exceptions.rent.RentNotFoundException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.TransactionSystemException;

import java.time.LocalDate;
import java.util.List;

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

    @Before
    public void cleanup() {
        rentService.deleteAllRents();
        bookCopyService.deleteAllBookCopies();
        bookService.deleteAllBooks();
        userService.deleteAllUsers();
    }

    @Test
    public void testGetRentById() {
        //Given
        Book testBook = new Book();
        testBook.setPublicationDate(LocalDate.now());
        testBook.setAuthor("Tolkien");
        testBook.setTitle("LOTR");
        bookService.addBook(testBook);

        BookCopy testCopy = new BookCopy();
        testCopy.setBook(testBook);
        testCopy.setAvailableForRent(true);
        testCopy.setStatus(BookCopyStatus.IN_USE);
        bookCopyService.addBookCopy(testCopy);

        User testUser = new User();
        testUser.setName("John");
        testUser.setSurname("Rambo");
        testUser.setHasAdminRights(false);
        testUser.setPrefferedCurrency("PLN");
        testUser.setRegistrationDate(LocalDate.now());
        testUser.setEmailAddress("ala@ala.pl");
        userService.addUser(testUser);

        Rent testRent = new Rent();
        testRent.setUser(testUser);
        testRent.setBookCopy(testCopy);
        testRent.setRentDate(LocalDate.now());
        testRent.setReturnDeadline(LocalDate.now().plusDays(30));
        rentService.addRent(testRent);

        //When
        long id = testRent.getId();
        Rent result = rentService.getRentById(id);

        //Then
        Assert.assertEquals(testRent, result);

    }

    @Test
    public void testGetAllRents() {
        //Given
        Book testBook = new Book();
        testBook.setPublicationDate(LocalDate.now());
        testBook.setAuthor("Tolkien");
        testBook.setTitle("LOTR");
        bookService.addBook(testBook);
        Book testBookTwo = new Book();
        testBookTwo.setPublicationDate(LocalDate.now());
        testBookTwo.setAuthor("Sapkowski");
        testBookTwo.setTitle("Wiedzmin");
        bookService.addBook(testBookTwo);

        BookCopy testCopy = new BookCopy();
        testCopy.setBook(testBook);
        testCopy.setAvailableForRent(true);
        testCopy.setStatus(BookCopyStatus.IN_USE);
        bookCopyService.addBookCopy(testCopy);
        BookCopy testCopyTwo = new BookCopy();
        testCopyTwo.setBook(testBookTwo);
        testCopyTwo.setAvailableForRent(true);
        testCopyTwo.setStatus(BookCopyStatus.IN_USE);
        bookCopyService.addBookCopy(testCopyTwo);

        User testUser = new User();
        testUser.setName("John");
        testUser.setSurname("Rambo");
        testUser.setHasAdminRights(false);
        testUser.setPrefferedCurrency("PLN");
        testUser.setEmailAddress("ala@ala.pl");
        testUser.setRegistrationDate(LocalDate.now());
        userService.addUser(testUser);

        Rent testRent = new Rent();
        testRent.setUser(testUser);
        testRent.setBookCopy(testCopy);
        testRent.setRentDate(LocalDate.now());
        testRent.setReturnDeadline(LocalDate.now().plusDays(30));
        rentService.addRent(testRent);
        Rent testRentTwo = new Rent();
        testRentTwo.setUser(testUser);
        testRentTwo.setBookCopy(testCopyTwo);
        testRentTwo.setRentDate(LocalDate.now());
        testRentTwo.setReturnDeadline(LocalDate.now().plusDays(30));
        rentService.addRent(testRentTwo);

        //When
        List<Rent> result = rentService.getAllRents();

        //Then
        Assert.assertEquals(2, result.size());
        Assert.assertEquals(testRent, result.get(0));
        Assert.assertEquals(testRentTwo, result.get(1));
    }

    @Test
    public void testSetRentAsReturned() {
        //Given
        Book testBook = new Book();
        testBook.setPublicationDate(LocalDate.now());
        testBook.setAuthor("Tolkien");
        testBook.setTitle("LOTR");
        bookService.addBook(testBook);

        BookCopy testCopy = new BookCopy();
        testCopy.setBook(testBook);
        testCopy.setAvailableForRent(true);
        testCopy.setStatus(BookCopyStatus.IN_USE);
        bookCopyService.addBookCopy(testCopy);

        User testUser = new User();
        testUser.setName("John");
        testUser.setSurname("Rambo");
        testUser.setHasAdminRights(false);
        testUser.setPrefferedCurrency("PLN");
        testUser.setEmailAddress("ala@ala.pl");
        testUser.setRegistrationDate(LocalDate.now());
        userService.addUser(testUser);

        Rent testRent = new Rent();
        testRent.setUser(testUser);
        testRent.setBookCopy(testCopy);
        testRent.setRentDate(LocalDate.now());
        testRent.setReturnDeadline(LocalDate.now().plusDays(30));
        rentService.addRent(testRent);
        long id = testRent.getId();

        //When
        rentService.setRentAsReturned(id);
        Rent resultRent = rentService.getRentById(id);
        BookCopy copyFromRent = resultRent.getBookCopy();

        //Then
        Assert.assertTrue(resultRent.isReturned());
        Assert.assertTrue(copyFromRent.isAvailableForRent());
    }

    @Test(expected = RentNotFoundException.class)
    public void testGettingRentByNonExistingId() {
        //Given & When
        long id = 1L;

        //Then
        Rent result = rentService.getRentById(id);
    }

    @Test(expected = CopyAlreadyRentedException.class)
    public void testAddingRentForAlreadyRentedCopy() {
        //Given
        //Given
        Book testBook = new Book();
        testBook.setPublicationDate(LocalDate.now());
        testBook.setAuthor("Tolkien");
        testBook.setTitle("LOTR");
        bookService.addBook(testBook);

        BookCopy testCopy = new BookCopy();
        testCopy.setBook(testBook);
        testCopy.setAvailableForRent(true);
        testCopy.setStatus(BookCopyStatus.IN_USE);
        bookCopyService.addBookCopy(testCopy);

        User testUser = new User();
        testUser.setName("John");
        testUser.setSurname("Rambo");
        testUser.setHasAdminRights(false);
        testUser.setPrefferedCurrency("PLN");
        testUser.setEmailAddress("ala@ala.pl");
        testUser.setRegistrationDate(LocalDate.now());
        userService.addUser(testUser);

        User testUserTwo = new User();
        testUserTwo.setName("John");
        testUserTwo.setSurname("Rambo");
        testUserTwo.setHasAdminRights(false);
        testUserTwo.setEmailAddress("ala@ala.pl");
        testUserTwo.setPrefferedCurrency("PLN");
        testUserTwo.setRegistrationDate(LocalDate.now());
        userService.addUser(testUserTwo);

        Rent testRent = new Rent();
        testRent.setUser(testUser);
        testRent.setBookCopy(testCopy);
        testRent.setRentDate(LocalDate.now());
        testRent.setReturnDeadline(LocalDate.now().plusDays(30));
        rentService.addRent(testRent);

        //When
        /**
         * Tries to create new rent for already rented book copy
         */
        Rent testRentToFail = new Rent();
        testRentToFail.setUser(testUserTwo);
        testRentToFail.setBookCopy(testCopy);
        testRentToFail.setRentDate(LocalDate.now());
        testRentToFail.setReturnDeadline(LocalDate.now().plusDays(30));
        rentService.addRent(testRentToFail);
    }

    @Test
    public void testCustomValidationForRentCodePassed() {
        //Given
        Book testBook = new Book();
        testBook.setPublicationDate(LocalDate.now());
        testBook.setAuthor("Tolkien");
        testBook.setTitle("LOTR");
        bookService.addBook(testBook);

        BookCopy testCopy = new BookCopy();
        testCopy.setBook(testBook);
        testCopy.setAvailableForRent(true);
        testCopy.setStatus(BookCopyStatus.IN_USE);
        bookCopyService.addBookCopy(testCopy);

        User testUser = new User();
        testUser.setName("John");
        testUser.setSurname("Rambo");
        testUser.setHasAdminRights(false);
        testUser.setPrefferedCurrency("PLN");
        testUser.setRegistrationDate(LocalDate.now());
        testUser.setEmailAddress("ala@ala.pl");
        userService.addUser(testUser);

        Rent testRent = new Rent();
        testRent.setUser(testUser);
        testRent.setBookCopy(testCopy);
        testRent.setRentDate(LocalDate.now());
        testRent.setReturnDeadline(LocalDate.now().plusDays(30));
        testRent.setRentCode("rentCode: blablabla");
        rentService.addRent(testRent);

        //When
        long id = testRent.getId();
        Rent result = rentService.getRentById(id);

        //Then
        Assert.assertEquals(testRent, result);

    }

    @Test(expected = TransactionSystemException.class)
    public void testCustomValidationForRentCodeFailed() {
        //Given
        Book testBook = new Book();
        testBook.setPublicationDate(LocalDate.now());
        testBook.setAuthor("Tolkien");
        testBook.setTitle("LOTR");
        bookService.addBook(testBook);

        BookCopy testCopy = new BookCopy();
        testCopy.setBook(testBook);
        testCopy.setAvailableForRent(true);
        testCopy.setStatus(BookCopyStatus.IN_USE);
        bookCopyService.addBookCopy(testCopy);

        User testUser = new User();
        testUser.setName("John");
        testUser.setSurname("Rambo");
        testUser.setHasAdminRights(false);
        testUser.setPrefferedCurrency("PLN");
        testUser.setRegistrationDate(LocalDate.now());
        testUser.setEmailAddress("ala@ala.pl");
        userService.addUser(testUser);

        Rent testRent = new Rent();
        testRent.setUser(testUser);
        testRent.setBookCopy(testCopy);
        testRent.setRentDate(LocalDate.now());
        testRent.setReturnDeadline(LocalDate.now().plusDays(30));
        testRent.setRentCode("blablabla");
        rentService.addRent(testRent);

        //When
        long id = testRent.getId();
        Rent result = rentService.getRentById(id);

        //Then
        Assert.assertEquals(testRent, result);
    }

    @Test
    public void testGettingUnpaidAndOutdatedRents() {
        // Given
        Book testBook = new Book();
        testBook.setPublicationDate(LocalDate.now());
        testBook.setAuthor("Tolkien");
        testBook.setTitle("LOTR");
        bookService.addBook(testBook);

        BookCopy testCopy = new BookCopy();
        testCopy.setBook(testBook);
        testCopy.setAvailableForRent(true);
        testCopy.setStatus(BookCopyStatus.IN_USE);
        bookCopyService.addBookCopy(testCopy);
        BookCopy testCopyTwo = new BookCopy();
        testCopyTwo.setBook(testBook);
        testCopyTwo.setAvailableForRent(true);
        testCopyTwo.setStatus(BookCopyStatus.IN_USE);
        bookCopyService.addBookCopy(testCopyTwo);
        BookCopy testCopyThree = new BookCopy();
        testCopyThree.setBook(testBook);
        testCopyThree.setAvailableForRent(true);
        testCopyThree.setStatus(BookCopyStatus.IN_USE);
        bookCopyService.addBookCopy(testCopyThree);
        BookCopy testCopyFour = new BookCopy();
        testCopyFour.setBook(testBook);
        testCopyFour.setAvailableForRent(true);
        testCopyFour.setStatus(BookCopyStatus.IN_USE);
        bookCopyService.addBookCopy(testCopyFour);

        User testUser = new User();
        testUser.setName("John");
        testUser.setSurname("Rambo");
        testUser.setHasAdminRights(false);
        testUser.setPrefferedCurrency("PLN");
        testUser.setEmailAddress("ala@ala.pl");
        testUser.setRegistrationDate(LocalDate.now());
        userService.addUser(testUser);

        Rent testRent = new Rent(); //not outdated
        testRent.setUser(testUser);
        testRent.setBookCopy(testCopy);
        testRent.setRentDate(LocalDate.now());
        testRent.setReturnDeadline(LocalDate.now().plusDays(30));
        rentService.addRent(testRent);
        Rent testRentTwo = new Rent(); //outdated
        testRentTwo.setUser(testUser);
        testRentTwo.setBookCopy(testCopyTwo);
        testRentTwo.setRentDate(LocalDate.of(2018, 11, 12));
        testRentTwo.setReturnDeadline(LocalDate.of(2019, 1, 12));
        rentService.addRent(testRentTwo);
        Rent testRentThree = new Rent(); //outdated
        testRentThree.setUser(testUser);
        testRentThree.setBookCopy(testCopyThree);
        testRentThree.setRentDate(LocalDate.of(2018, 11, 12));
        testRentThree.setReturnDeadline(LocalDate.of(2019, 1, 12));
        rentService.addRent(testRentThree);
        Rent testRentFour = new Rent(); //outdated but returned
        testRentFour.setUser(testUser);
        testRentFour.setBookCopy(testCopyFour);
        testRentFour.setRentDate(LocalDate.of(2018, 11, 12));
        testRentFour.setReturnDeadline(LocalDate.of(2019, 1, 12));
        testRentFour.setReturned(true);
        rentService.addRent(testRentFour);

        //When
        List<Rent> outdated = rentService.getUnpaidAndOutdated();
        long outdatedNumber = outdated.size();

        //Then
        Assert.assertEquals(2, outdatedNumber);
        Assert.assertEquals(testRentTwo, outdated.get(0));
        Assert.assertEquals(testRentThree, outdated.get(1));
    }
}