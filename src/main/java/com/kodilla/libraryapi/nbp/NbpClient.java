package com.kodilla.libraryapi.nbp;

import com.kodilla.libraryapi.domain.dto.CurrencyDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Optional;

@Component
public class NbpClient {
    private static final Logger LOGGER = LoggerFactory.getLogger(NbpClient.class);

    @Autowired
    private RestTemplate restTemplate;

    public CurrencyDto getCurrency(String currencyCode) {
        URI url = UriComponentsBuilder.fromHttpUrl("http://api.nbp.pl/api/exchangerates/rates/A/" + currencyCode)
                .queryParam("format", "json")
                .build().encode().toUri();

        try{
            CurrencyDto response = restTemplate.getForObject(url, CurrencyDto.class);
            return Optional.ofNullable(response).orElse(new CurrencyDto());
        } catch(RestClientException e) {
            LOGGER.error(e.getMessage(), e);
            return new CurrencyDto();
        }
    }
}
