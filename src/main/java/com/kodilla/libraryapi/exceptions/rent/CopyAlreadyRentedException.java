package com.kodilla.libraryapi.exceptions.rent;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.CONFLICT, reason="This book copy is already rented by someone else")
public class CopyAlreadyRentedException extends RuntimeException {
}
