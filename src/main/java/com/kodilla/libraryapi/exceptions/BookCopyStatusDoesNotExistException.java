package com.kodilla.libraryapi.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "Given string does not match any available status")
public class BookCopyStatusDoesNotExistException extends RuntimeException {
}
