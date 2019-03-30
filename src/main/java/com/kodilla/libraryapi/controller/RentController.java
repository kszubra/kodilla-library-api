package com.kodilla.libraryapi.controller;

import com.kodilla.libraryapi.mapper.RentMapper;
import com.kodilla.libraryapi.service.RentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rents")
public class RentController {
    private final RentService rentService;
    private final RentMapper rentMapper;

    @Autowired
    public RentController(RentService rentService, RentMapper rentMapper) {
        this.rentService = rentService;
        this.rentMapper = rentMapper;
    }
}
