package com.kodilla.libraryapi.service;

import com.kodilla.libraryapi.domain.Rent;
import com.kodilla.libraryapi.exceptions.RentNotFoundException;
import com.kodilla.libraryapi.repository.RentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class RentService {
    @Autowired
    private RentRepository rentRepository;
    @Autowired
    private BookCopyService bookCopyService;

    public Rent addRent(final Rent rent) {
        return rentRepository.save(rent);
    }

    public Rent getRentById(final long id) {
        return rentRepository.findById(id).orElseThrow(RentNotFoundException::new);
    }

    public List<Rent> getAllRents() {
        return rentRepository.findAll();
    }

    @Transactional
    public void setRentAsReturned(final long id) {
        Rent rent = rentRepository.findById(id).orElseThrow(RentNotFoundException::new);
        rent.setReturned(true);
        bookCopyService.setAsReturned(rent.getBookCopy().getId());
    }

    public void deleteAllRents() {
        rentRepository.deleteAll();
    }
}
