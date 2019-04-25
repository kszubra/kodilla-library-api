package com.kodilla.libraryapi.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason="Book copy of given id does not exist")
public class BookCopyNotFoundException extends RuntimeException {
}
