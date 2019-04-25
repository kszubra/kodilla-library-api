package com.kodilla.libraryapi.repository;

import com.kodilla.libraryapi.domain.Fine;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FineRepository extends CrudRepository<Fine, Long> {

    @Override
    Fine save(Fine fine);

    @Override
    Optional<Fine> findById(Long id);

}
