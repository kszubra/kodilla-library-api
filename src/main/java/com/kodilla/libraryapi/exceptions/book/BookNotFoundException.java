package com.kodilla.libraryapi.exceptions.book;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason="Book of given id does not exist")
public class BookNotFoundException extends RuntimeException {
}
