package com.kodilla.libraryapi.service;

import com.kodilla.libraryapi.domain.Fine;
import com.kodilla.libraryapi.exceptions.FineNotFoundException;
import com.kodilla.libraryapi.repository.FineRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class FineService {
    private final FineRepository fineRepository;

    public Fine addFine(final Fine fine) {
        return fineRepository.save(fine);
    }

    public Fine getFineById(final long id) {
        return fineRepository.findById(id).orElseThrow(FineNotFoundException::new);
    }

    public Fine setFineAsPaid(final long id) {
        Fine fine = fineRepository.findById(id).orElseThrow(FineNotFoundException::new);
        fine.setPaid(true);

        return fineRepository.save(fine);
    }
}
