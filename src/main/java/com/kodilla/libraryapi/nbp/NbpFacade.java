package com.kodilla.libraryapi.nbp;

import com.kodilla.libraryapi.domain.Currency;
import com.kodilla.libraryapi.enumerics.CurrencyEnum;
import com.kodilla.libraryapi.mapper.CurrencyMapper;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class NbpFacade {
    private final NbpClient nbpClient;
    private final CurrencyMapper currencyMapper;

    public double getUSDRate() {
        return currencyMapper.mapToCurrency(nbpClient.getCurrency(CurrencyEnum.USD.toString())).getValue();
    }

    public double getCADRate() {
        return currencyMapper.mapToCurrency(nbpClient.getCurrency(CurrencyEnum.CAD.toString())).getValue();
    }

    public double getGBPRate() {
        return currencyMapper.mapToCurrency(nbpClient.getCurrency(CurrencyEnum.GBP.toString())).getValue();
    }

    public double getEURRate() {
        return currencyMapper.mapToCurrency(nbpClient.getCurrency(CurrencyEnum.EUR.toString())).getValue();
    }

}
