package org.example.repository;

import org.example.model.InternalPrice;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class PriceFileRepositoryTest {
    private PriceFileRepository repository;

    @Before
    public void setUp() {
        repository = new PriceFileRepository();
    }

    @Test
    public void testDataLoading(){
        repository.read();
        List<InternalPrice> prices = repository.getPrices();
        Assert.assertEquals(5, prices.size());

        InternalPrice internalPrice1 = prices.get(0);
        Assert.assertEquals("EUR/USD", internalPrice1.getCcyPair());
        Assert.assertEquals("1.1000", internalPrice1.getBid());
        Assert.assertEquals("1.2000", internalPrice1.getAsk());

        InternalPrice internalPrice2 = prices.get(1);
        Assert.assertEquals("EUR/JPY", internalPrice2.getCcyPair());
        Assert.assertEquals("119.60", internalPrice2.getBid());
        Assert.assertEquals("119.90", internalPrice2.getAsk());

        InternalPrice internalPrice3 = prices.get(2);
        Assert.assertEquals("GBP/USD", internalPrice3.getCcyPair());
        Assert.assertEquals("1.2500", internalPrice3.getBid());
        Assert.assertEquals("1.2560", internalPrice3.getAsk());

        InternalPrice internalPrice4 = prices.get(3);
        Assert.assertEquals("GBP/USD", internalPrice4.getCcyPair());
        Assert.assertEquals("1.2499", internalPrice4.getBid());
        Assert.assertEquals("1.2561", internalPrice4.getAsk());

        InternalPrice internalPrice5 = prices.get(4);
        Assert.assertEquals("EUR/JPY", internalPrice5.getCcyPair());
        Assert.assertEquals("119.61", internalPrice5.getBid());
        Assert.assertEquals("119.91", internalPrice5.getAsk());
    }
}
