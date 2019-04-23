package com.kodilla.libraryapi.controller;

import com.kodilla.libraryapi.domain.dto.UserDto;
import com.kodilla.libraryapi.mapper.UserMapper;
import com.kodilla.libraryapi.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/users")
@AllArgsConstructor
public class UserController {
    private final UserService userService;
    private final UserMapper userMapper;


    @PostMapping("addUser")
    public void addUser(@RequestBody UserDto dto) {
        userService.addUser(userMapper.mapToUser(dto));
    }
}
