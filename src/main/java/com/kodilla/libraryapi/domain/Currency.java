package com.kodilla.libraryapi.domain;

import com.kodilla.libraryapi.enumerics.CurrencyEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder(toBuilder = true)
public class Currency {
    private String name;
    private String code;
    private double value;
}
