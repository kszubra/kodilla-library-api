package com.kodilla.libraryapi.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason="User of given id does not exist")
public class UserNotFoundException extends RuntimeException {
}
