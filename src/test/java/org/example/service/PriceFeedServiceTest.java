package org.example.service;

import org.example.repository.PriceRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

public class PriceFeedServiceTest {
    PriceFeedService priceFeedService;
    PriceRepository priceRepository;

    @Before
    public void setUp(){
        priceRepository = Mockito.mock(PriceRepository.class);
        priceFeedService = new PriceFeedServiceImpl(priceRepository);
    }

    @Test
    public void testStart() {
        priceFeedService.start();
        Mockito.verify(priceRepository).loadData();
    }

    @Test
    public void testStop() {
        priceFeedService.stop();
        Mockito.verify(priceRepository).stopLoading();
    }
}
