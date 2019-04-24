package com.kodilla.libraryapi.nbp;


import com.kodilla.libraryapi.enumerics.CurrencyEnum;
import com.kodilla.libraryapi.mapper.CurrencyMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class NbpFacade {
    private final NbpClient nbpClient;
    private final CurrencyMapper currencyMapper;

    private double getValue(CurrencyEnum curEnum) {
        return currencyMapper.mapToCurrency(nbpClient.getCurrency(curEnum.toString())).getValue();
    }

    public double getUSDRate() {
        return getValue(CurrencyEnum.USD);
    }

    public double getCADRate() {
        return getValue(CurrencyEnum.CAD);
    }

    public double getGBPRate() {
        return getValue(CurrencyEnum.GBP);
    }

    public double getEURRate() {
        return getValue(CurrencyEnum.EUR);
    }

}
