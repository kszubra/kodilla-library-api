package com.kodilla.libraryapi.exceptions.book;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.CONFLICT)
public class BookAlreadyExistsException extends RuntimeException {
}
