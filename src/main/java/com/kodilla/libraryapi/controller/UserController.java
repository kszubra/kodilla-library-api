package com.kodilla.libraryapi.controller;

import com.kodilla.libraryapi.domain.dto.UserDto;
import com.kodilla.libraryapi.mapper.UserMapper;
import com.kodilla.libraryapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;
    private final UserMapper userMapper;

    @Autowired
    public UserController(UserService userService, UserMapper userMapper) {
        this.userService = userService;
        this.userMapper = userMapper;
    }

    @PostMapping("addUser")
    public void addUser(@RequestBody UserDto dto) {
        userService.addUser(userMapper.mapToUser(dto));
    }
}
