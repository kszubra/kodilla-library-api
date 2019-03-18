package com.kodilla.libraryapi.repository;

import com.kodilla.libraryapi.domain.Book;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends CrudRepository<Book, Long> {

    @Override
    Book save(Book book);

    @Override
    Optional<Book> findById(Long id);

    @Override
    List<Book> findAll();

    @Query
    Optional<Book> getBookByTitle(@Param("TITLE") String title);
}
