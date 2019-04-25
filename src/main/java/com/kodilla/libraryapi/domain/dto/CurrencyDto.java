package com.kodilla.libraryapi.domain.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class CurrencyDto {

    @JsonProperty("code")
    private String currencyCode;

    @JsonProperty("currency")
    private String currency;

    @JsonProperty("rates")
    private List<NbpRateDto> rates;
}
