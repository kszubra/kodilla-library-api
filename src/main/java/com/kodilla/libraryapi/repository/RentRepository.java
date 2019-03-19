package com.kodilla.libraryapi.repository;

import com.kodilla.libraryapi.domain.Rent;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RentRepository extends CrudRepository<Rent, Long> {

    @Override
    Rent save(Rent rent);

    @Override
    Optional<Rent> findById(Long id);

}
