package com.kodilla.libraryapi.repository;

import com.kodilla.libraryapi.domain.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Long> {

    @Override
    User save(User user);

    @Override
    Optional<User> findById(Long id);
}
