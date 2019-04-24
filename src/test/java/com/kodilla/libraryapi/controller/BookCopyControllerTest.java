package com.kodilla.libraryapi.controller;

import com.google.gson.Gson;
import com.kodilla.libraryapi.domain.Book;
import com.kodilla.libraryapi.domain.BookCopy;
import com.kodilla.libraryapi.domain.dto.BookCopyDto;
import com.kodilla.libraryapi.domain.dto.BookDto;
import com.kodilla.libraryapi.enumerics.BookCopyStatus;
import com.kodilla.libraryapi.mapper.BookCopyMapper;
import com.kodilla.libraryapi.mapper.BookMapper;
import com.kodilla.libraryapi.service.BookCopyService;
import com.kodilla.libraryapi.service.BookService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;

import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(BookCopyController.class)
public class BookCopyControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private BookService bookService;
    @MockBean
    private BookCopyMapper bookCopyMapper;
    @MockBean
    private BookCopyService bookCopyService;
    @MockBean
    private BookMapper bookMapper;

    private Gson gson = new Gson();

    @Test
    public void testAddBookCopy() throws Exception {
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

        String jsonContent = gson.toJson(copyDto);

        // When & Then
        mockMvc.perform(post("/library/copies").contentType(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonContent))
                .andExpect(status().isOk());
    }

    @Test
    public void testUpdateBookCopyStatus() throws Exception {
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

        String jsonContent = gson.toJson(copyDto);

        // When & Then
        mockMvc.perform(put("/library/copies?id=2&status=destroyed").contentType(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonContent))
                .andExpect(status().isOk());
    }

    @Test
    public void testGetNumberOfFreeCopies() throws Exception {
        Book book = Book.builder()
                .id(1)
                .author("Someone")
                .title("Fancy title")
                .publicationDate(LocalDate.now())
                .build();
        long bookId = book.getId();
        when(bookService.addBook(book)).thenReturn(book);

        BookDto bookDto = BookDto.builder()
                .id(1)
                .author("Someone")
                .title("Fancy title")
                .publicationDate(LocalDate.now())
                .build();
        when(bookMapper.mapToBook(bookDto)).thenReturn(book);

        BookCopy copyOne = BookCopy.builder()
                .id(2)
                .book(book)
                .availableForRent(true)
                .status(BookCopyStatus.IN_USE)
                .rents(null)
                .build();
        when(bookCopyService.addBookCopy(copyOne)).thenReturn(copyOne);
        BookCopyDto copyOneDto = BookCopyDto.builder()
                .id(2)
                .bookId(1)
                .status(BookCopyStatus.IN_USE.toString())
                .rents(null)
                .availableForRent(true)
                .build();
        when(bookCopyMapper.mapToBookCopy(copyOneDto)).thenReturn(copyOne);

        BookCopy copyTwo = BookCopy.builder()
                .id(3)
                .book(book)
                .availableForRent(false)
                .status(BookCopyStatus.DESTROYED)
                .rents(null)
                .build();
        when(bookCopyService.addBookCopy(copyTwo)).thenReturn(copyTwo);
        BookCopyDto copyTwoDto = BookCopyDto.builder()
                .id(3)
                .bookId(1)
                .status(BookCopyStatus.DESTROYED.toString())
                .rents(null)
                .availableForRent(false)
                .build();
        when(bookCopyMapper.mapToBookCopy(copyTwoDto)).thenReturn(copyTwo);

        when(bookCopyService.getNumberOfFreeCopiesByBookId(bookId)).thenReturn(1L);

        // When & Then
        mockMvc.perform(get("/library/copies/" + bookId))
                .andExpect(jsonPath("$", is(1)))
                .andExpect(status().isOk());
        verify(bookCopyService, times(1)).getNumberOfFreeCopiesByBookId(bookId);
    }
}