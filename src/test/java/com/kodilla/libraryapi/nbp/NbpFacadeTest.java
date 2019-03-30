package com.kodilla.libraryapi.nbp;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class NbpFacadeTest {
    @Autowired
    private NbpFacade nbpFacade;

    private double usdRate;
    private double cadRate;
    private double gbpRate;
    private double eurRate;

    @Before
    public void assignValues() {
        usdRate = nbpFacade.getUSDRate();
        cadRate = nbpFacade.getCADRate();
        gbpRate = nbpFacade.getGBPRate();
        eurRate = nbpFacade.getEURRate();
    }

    @Test
    public void testGetUSDRate() {
        //Then
        Assert.assertTrue(usdRate > 0);
        Assert.assertNotEquals(usdRate, cadRate);
        Assert.assertNotEquals(usdRate, gbpRate);
        Assert.assertNotEquals(usdRate, eurRate);
    }

    @Test
    public void testGetCADRate() {
        //Then
        Assert.assertTrue(cadRate > 0);
        Assert.assertNotEquals(cadRate, usdRate);
        Assert.assertNotEquals(cadRate, gbpRate);
        Assert.assertNotEquals(cadRate, eurRate);
    }

    @Test
    public void testGetGBPRate() {
        //Then
        Assert.assertTrue(gbpRate > 0);
        Assert.assertNotEquals(gbpRate, usdRate);
        Assert.assertNotEquals(gbpRate, cadRate);
        Assert.assertNotEquals(gbpRate, eurRate);
    }

    @Test
    public void testGetEURRate() {
        //Then
        Assert.assertTrue(eurRate > 0);
        Assert.assertNotEquals(eurRate, usdRate);
        Assert.assertNotEquals(eurRate, cadRate);
        Assert.assertNotEquals(eurRate, gbpRate);
    }
}