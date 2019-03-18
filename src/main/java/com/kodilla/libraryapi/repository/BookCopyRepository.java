package com.kodilla.libraryapi.repository;

import com.kodilla.libraryapi.domain.BookCopy;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface BookCopyRepository extends CrudRepository<BookCopy, Long> {

    @Override
    BookCopy save(BookCopy bookCopy);

    @Override
    Optional<BookCopy> findById(Long id);

    @Override
    List<BookCopy> findAll();
}
