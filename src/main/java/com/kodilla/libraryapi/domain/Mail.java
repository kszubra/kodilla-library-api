package com.kodilla.libraryapi.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;


@Getter
@AllArgsConstructor
@Builder(toBuilder = true)
public class Mail {
    private String mailTo;
    private String toCc;
    private String subject;
    private String message;
}
