package com.kodilla.libraryapi.controller;

import com.google.gson.Gson;
import com.kodilla.libraryapi.domain.Book;
import com.kodilla.libraryapi.domain.BookCopy;
import com.kodilla.libraryapi.domain.Rent;
import com.kodilla.libraryapi.domain.User;
import com.kodilla.libraryapi.domain.dto.BookCopyDto;
import com.kodilla.libraryapi.domain.dto.BookDto;
import com.kodilla.libraryapi.domain.dto.RentDto;
import com.kodilla.libraryapi.domain.dto.UserDto;
import com.kodilla.libraryapi.enumerics.BookCopyStatus;
import com.kodilla.libraryapi.mapper.BookCopyMapper;
import com.kodilla.libraryapi.mapper.BookMapper;
import com.kodilla.libraryapi.mapper.RentMapper;
import com.kodilla.libraryapi.mapper.UserMapper;
import com.kodilla.libraryapi.service.BookCopyService;
import com.kodilla.libraryapi.service.BookService;
import com.kodilla.libraryapi.service.RentService;
import com.kodilla.libraryapi.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(RentController.class)
public class RentControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private BookMapper bookMapper;
    @MockBean
    private BookService bookService;
    @MockBean
    private BookCopyMapper bookCopyMapper;
    @MockBean
    private BookCopyService bookCopyService;
    @MockBean
    private UserMapper userMapper;
    @MockBean
    private UserService userService;
    @MockBean
    private RentMapper rentMapper;
    @MockBean
    private RentService rentService;

    private Gson gson = new Gson();

    @Test
    public void testNewRent() throws Exception {
        //Given
        Book book = Book.builder()
                .id(1)
                .author("Someone")
                .title("Fancy title")
                .publicationDate(LocalDate.now())
                .build();
        BookDto bookDto = BookDto.builder()
                .id(1)
                .author("Someone")
                .title("Fancy title")
                .publicationDate(LocalDate.now())
                .build();
        when(bookMapper.mapToBook(bookDto)).thenReturn(book);
        when(bookService.addBook(book)).thenReturn(book);
        when(bookService.getBookById(1)).thenReturn(book);

        BookCopy copy = BookCopy.builder()
                .id(2)
                .book(book)
                .availableForRent(true)
                .status(BookCopyStatus.IN_USE)
                .rents(null)
                .build();
        BookCopyDto copyDto = BookCopyDto.builder()
                .id(2)
                .bookId(1)
                .status(BookCopyStatus.IN_USE.toString())
                .rents(null)
                .availableForRent(true)
                .build();
        when(bookCopyMapper.mapToBookCopy(copyDto)).thenReturn(copy);
        when(bookCopyService.addBookCopy(copy)).thenReturn(copy);
        when(bookCopyService.getBookCopyById(2)).thenReturn(copy);

        User user = User.builder()
                .id(3)
                .name("John")
                .surname("Rambo")
                .hasAdminRights(false)
                .emailAddress("rambo@rambo.com")
                .prefferedCurrency("EUR")
                .registrationDate(LocalDate.now())
                .build();

        UserDto userDto = UserDto.builder()
                .id(3)
                .name("John")
                .surname("Rambo")
                .hasAdminRights(false)
                .emailAddress("rambo@rambo.com")
                .prefferedCurrency("EUR")
                .registrationDate(LocalDate.now())
                .build();
        when(userMapper.mapToUser(userDto)).thenReturn(user);
        when(userService.addUser(user)).thenReturn(user);
        when(userService.getUserById(3)).thenReturn(user);

        Rent rent = Rent.builder()
                .id(4)
                .bookCopy(copy)
                .user(user)
                .fine(null)
                .rentDate(LocalDate.now())
                .returnDeadline(LocalDate.now().plusDays(30))
                .returned(false)
                .rentCode("rentCode: 21321")
                .build();
        RentDto rentDto = RentDto.builder()
                .id(4)
                .userId(3)
                .bookCopyId(2)
                .fineId(0)
                .rentDate(LocalDate.now())
                .returnDeadline(LocalDate.now().plusDays(30))
                .returned(false)
                .rentCode("rentCode: 21321")
                .build();
        when(rentMapper.mapToRent(rentDto)).thenReturn(rent);
        when(rentService.addRent(rent)).thenReturn(rent);
        when(rentService.getRentById(4)).thenReturn(rent);

        String jsonContent = gson.toJson(rentDto);

        // When & Then
        mockMvc.perform(post("/library/rents").contentType(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonContent))
                .andExpect(status().isOk());

    }

    @Test
    public void testReturnRent() throws Exception {
        //Given
        Book book = Book.builder()
                .id(1)
                .author("Someone")
                .title("Fancy title")
                .publicationDate(LocalDate.now())
                .build();
        BookDto bookDto = BookDto.builder()
                .id(1)
                .author("Someone")
                .title("Fancy title")
                .publicationDate(LocalDate.now())
                .build();
        when(bookMapper.mapToBook(bookDto)).thenReturn(book);
        when(bookService.addBook(book)).thenReturn(book);
        when(bookService.getBookById(1)).thenReturn(book);

        BookCopy copy = BookCopy.builder()
                .id(2)
                .book(book)
                .availableForRent(true)
                .status(BookCopyStatus.IN_USE)
                .rents(null)
                .build();
        BookCopyDto copyDto = BookCopyDto.builder()
                .id(2)
                .bookId(1)
                .status(BookCopyStatus.IN_USE.toString())
                .rents(null)
                .availableForRent(true)
                .build();
        when(bookCopyMapper.mapToBookCopy(copyDto)).thenReturn(copy);
        when(bookCopyService.addBookCopy(copy)).thenReturn(copy);
        when(bookCopyService.getBookCopyById(2)).thenReturn(copy);

        User user = User.builder()
                .id(3)
                .name("John")
                .surname("Rambo")
                .hasAdminRights(false)
                .emailAddress("rambo@rambo.com")
                .prefferedCurrency("EUR")
                .registrationDate(LocalDate.now())
                .build();

        UserDto userDto = UserDto.builder()
                .id(3)
                .name("John")
                .surname("Rambo")
                .hasAdminRights(false)
                .emailAddress("rambo@rambo.com")
                .prefferedCurrency("EUR")
                .registrationDate(LocalDate.now())
                .build();
        when(userMapper.mapToUser(userDto)).thenReturn(user);
        when(userService.addUser(user)).thenReturn(user);
        when(userService.getUserById(3)).thenReturn(user);

        Rent rent = Rent.builder()
                .id(4)
                .bookCopy(copy)
                .user(user)
                .fine(null)
                .rentDate(LocalDate.now())
                .returnDeadline(LocalDate.now().plusDays(30))
                .returned(false)
                .rentCode("rentCode: 21321")
                .build();
        RentDto rentDto = RentDto.builder()
                .id(4)
                .userId(3)
                .bookCopyId(2)
                .fineId(0)
                .rentDate(LocalDate.now())
                .returnDeadline(LocalDate.now().plusDays(30))
                .returned(false)
                .rentCode("rentCode: 21321")
                .build();
        when(rentMapper.mapToRent(rentDto)).thenReturn(rent);
        when(rentService.addRent(rent)).thenReturn(rent);
        when(rentService.getRentById(4)).thenReturn(rent);

        String jsonContent = gson.toJson(rentDto);

        // When & Then
        mockMvc.perform(put("/library/rents/4").contentType(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonContent))
                .andExpect(status().isOk());
    }

}