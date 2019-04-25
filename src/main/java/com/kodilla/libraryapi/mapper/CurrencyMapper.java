package com.kodilla.libraryapi.mapper;

import com.kodilla.libraryapi.domain.Currency;
import com.kodilla.libraryapi.domain.dto.CurrencyDto;
import org.springframework.stereotype.Component;

@Component
public class CurrencyMapper {

    public Currency mapToCurrency(CurrencyDto dto) {
        return Currency.builder()
                .code(dto.getCurrencyCode())
                .name(dto.getCurrency())
                .value(dto.getRates().get(0).getRateValue())
                .build();
    }

}
