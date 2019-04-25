package com.kodilla.libraryapi.nbp;

import com.kodilla.libraryapi.domain.dto.CurrencyDto;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Optional;

@Component
@AllArgsConstructor
@Slf4j
public class NbpClient {
    private final RestTemplate restTemplate;

    public CurrencyDto getCurrency(String currencyCode) {
        URI url = UriComponentsBuilder.fromHttpUrl("http://api.nbp.pl/api/exchangerates/rates/A/" + currencyCode)
                .queryParam("format", "json")
                .build().encode().toUri();

        try{
            CurrencyDto response = restTemplate.getForObject(url, CurrencyDto.class);
            return Optional.ofNullable(response).orElse(new CurrencyDto());
        } catch(RestClientException e) {
            log.error(e.getMessage(), e);
            return new CurrencyDto();
        }
    }
}
