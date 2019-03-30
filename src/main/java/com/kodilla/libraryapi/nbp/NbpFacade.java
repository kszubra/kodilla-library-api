package com.kodilla.libraryapi.nbp;

import com.kodilla.libraryapi.domain.Currency;
import com.kodilla.libraryapi.mapper.CurrencyMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class NbpFacade {
    private final NbpClient nbpClient;
    private final CurrencyMapper currencyMapper;

    @Autowired
    public NbpFacade(NbpClient nbpClient, CurrencyMapper currencyMapper) {
        this.nbpClient = nbpClient;
        this.currencyMapper = currencyMapper;
    }

    public double getUSDRate() {
        return currencyMapper.mapToCurrency(nbpClient.getCurrency(Currency.CURRENCY_CODE_US_DOLLAR)).getValue();
    }

    public double getCADRate() {
        return currencyMapper.mapToCurrency(nbpClient.getCurrency(Currency.CURRENCY_CODE_CANADIAN_DOLLAR)).getValue();
    }

    public double getGBPRate() {
        return currencyMapper.mapToCurrency(nbpClient.getCurrency(Currency.CURRENCY_CODE_GREAT_BRITAIN_POUND)).getValue();
    }

    public double getEURRate() {
        return currencyMapper.mapToCurrency(nbpClient.getCurrency(Currency.CURRENCY_CODE_EURO)).getValue();
    }

}
