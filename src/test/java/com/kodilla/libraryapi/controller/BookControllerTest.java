package com.kodilla.libraryapi.controller;

import com.google.gson.Gson;
import com.kodilla.libraryapi.domain.Book;
import com.kodilla.libraryapi.domain.dto.BookDto;
import com.kodilla.libraryapi.mapper.BookMapper;
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

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(BookController.class)
public class BookControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private BookService bookService;
    @MockBean
    private BookMapper bookMapper;

    private Gson gson = new Gson();

    @Test
    public void testAddBook() throws Exception {
        // Given
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
        String jsonContent = gson.toJson(bookDto);

        // When & Then
        mockMvc.perform(post("/books").contentType(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonContent))
                .andExpect(status().isOk());

        verify(bookMapper, times(1)).mapToBook(bookDto);
        verify(bookService, times(1)).addBook(book);
    }

}