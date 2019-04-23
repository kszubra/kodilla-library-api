package com.kodilla.libraryapi.controller;

import com.kodilla.libraryapi.domain.dto.RentDto;
import com.kodilla.libraryapi.mapper.RentMapper;
import com.kodilla.libraryapi.service.RentService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/rents")
@AllArgsConstructor
public class RentController {
    private final RentService rentService;
    private final RentMapper rentMapper;

    @Transactional
    @PostMapping("newRent")
    public void newRent(@RequestBody RentDto dto) {
        rentService.addRent( rentMapper.mapToRent(dto) );
    }

    @Transactional
    @PutMapping("return")
    public void returnRent(@RequestParam("id") long rentId) {
        rentService.setRentAsReturned(rentId);
    }

}
