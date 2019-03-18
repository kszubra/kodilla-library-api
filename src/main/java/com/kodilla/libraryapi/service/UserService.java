package com.kodilla.libraryapi.service;

import com.kodilla.libraryapi.domain.User;
import com.kodilla.libraryapi.exceptions.UserNotFoundException;
import com.kodilla.libraryapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public User addUser(final User user) {
        return userRepository.save(user);
    }

    public User getUserById(final long id) {
        return userRepository.findById(id).orElseThrow(UserNotFoundException::new);
    }
}
