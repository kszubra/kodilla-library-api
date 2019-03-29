package com.kodilla.libraryapi.exceptions.rent;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.CONFLICT)
public class CopyAlreadyRentedException extends RuntimeException {
}
