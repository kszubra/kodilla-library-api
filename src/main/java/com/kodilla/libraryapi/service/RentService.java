package com.kodilla.libraryapi.service;

import com.kodilla.libraryapi.domain.BookCopy;
import com.kodilla.libraryapi.domain.Rent;
import com.kodilla.libraryapi.exceptions.rent.CopyAlreadyRentedException;
import com.kodilla.libraryapi.exceptions.rent.RentNotFoundException;
import com.kodilla.libraryapi.repository.BookCopyRepository;
import com.kodilla.libraryapi.repository.RentRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@AllArgsConstructor
public class RentService {
    private final RentRepository rentRepository;
    private final BookCopyService bookCopyService;
    private final BookCopyRepository bookCopyRepository;

    private boolean notAvailable(BookCopy copy) {
        return !copy.isAvailableForRent();
    }

    @Transactional
    public Rent addRent(final Rent rent) {
        BookCopy bookCopy = rent.getBookCopy();

        if (notAvailable(bookCopy)) {
            throw new CopyAlreadyRentedException();
        }

        bookCopy.setAvailableForRent(false);
        bookCopyRepository.save(bookCopy);

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

    public List<Rent> getUnpaidAndOutdated() {
        return rentRepository.findAllByReturnDeadlineBeforeAndReturnedIsFalse(LocalDate.now());
    }

    public void deleteAllRents() {
        rentRepository.deleteAll();
    }
}
