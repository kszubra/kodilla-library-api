package com.kodilla.libraryapi.repository;

import com.kodilla.libraryapi.domain.Rent;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface RentRepository extends CrudRepository<Rent, Long> {

    @Override
    Rent save(Rent rent);

    @Override
    List<Rent> findAll();

    @Override
    Optional<Rent> findById(Long id);

    List<Rent> findAllByReturnDeadlineBeforeAndReturnedIsFalse(LocalDate current);

}
