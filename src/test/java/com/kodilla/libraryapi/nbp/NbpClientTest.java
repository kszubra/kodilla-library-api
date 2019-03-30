package com.kodilla.libraryapi.nbp;

import com.kodilla.libraryapi.domain.dto.CurrencyDto;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class NbpClientTest {
    @Autowired
    private NbpClient nbpClient;

    @Test
    public void testGetCurrency() {
        //Given & When
        CurrencyDto currencyResponse = nbpClient.getCurrency("USD");

        //Then
        Assert.assertTrue(currencyResponse.getRates().get(0).getRateValue() > 0);
        System.out.println(currencyResponse.getRates().get(0).getRateValue());
        Assert.assertEquals("USD", currencyResponse.getCurrencyCode());
        Assert.assertEquals("dolar amerykański", currencyResponse.getCurrency());
    }
}