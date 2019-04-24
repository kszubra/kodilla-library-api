package com.kodilla.libraryapi.controller;

import com.google.gson.Gson;
import com.kodilla.libraryapi.domain.User;
import com.kodilla.libraryapi.domain.dto.UserDto;
import com.kodilla.libraryapi.mapper.UserMapper;
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

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(UserController.class)
public class UserControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private UserService userService;
    @MockBean
    private UserMapper userMapper;

    private Gson gson = new Gson();

    @Test
    public void testAddUser() throws Exception {
        //Given
        User user = User.builder()
                .id(1)
                .name("John")
                .surname("Rambo")
                .hasAdminRights(false)
                .emailAddress("rambo@rambo.com")
                .prefferedCurrency("EUR")
                .registrationDate(LocalDate.now())
                .build();

        UserDto dto = UserDto.builder()
                .id(1)
                .name("John")
                .surname("Rambo")
                .hasAdminRights(false)
                .emailAddress("rambo@rambo.com")
                .prefferedCurrency("EUR")
                .registrationDate(LocalDate.now())
                .build();
        when(userMapper.mapToUser(dto)).thenReturn(user);
        when(userService.addUser(user)).thenReturn(user);

        String jsonContent = gson.toJson(dto);

        // When & Then
        mockMvc.perform(post("/users").contentType(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonContent))
                .andExpect(status().isOk());
    }
}