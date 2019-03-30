package com.kodilla.libraryapi.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder(toBuilder = true)
public class Currency {
    public static final String CURRENCY_CODE_US_DOLLAR = "USD";
    public static final String CURRENCY_CODE_CANADIAN_DOLLAR = "CAD";
    public static final String CURRENCY_CODE_EURO = "EUR";
    public static final String CURRENCY_CODE_GREAT_BRITAIN_POUND = "GBP";
    public static final String CURRENCY_CODE_POLISH_ZLOTY = "PLN";

    private String name;
    private String code;
    private double value;
}
