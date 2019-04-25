package com.kodilla.libraryapi.exceptions.rent;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason="Rent of given id does not exist")
public class RentNotFoundException extends RuntimeException {
}
