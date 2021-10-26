package org.example;

import org.example.event.EventType;
import org.example.event.PriceEvent;
import org.example.handler.ClientPricePublisher;
import org.example.handler.PriceEventHandler;
import org.example.model.InternalPrice;
import org.example.model.PriceBuilder;
import org.example.model.PriceDto;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

import java.time.LocalDateTime;

public class PriceEventHandlerTest {

    private PriceEventHandler priceEventHandler;
    private ClientPricePublisher clientPricePublisher;

    @Before
    public void setUp() {
        clientPricePublisher = Mockito.mock(ClientPricePublisher.class);
        priceEventHandler = new PriceEventHandler(clientPricePublisher);
    }

    @Test
    public void testMargin() throws Exception{
        InternalPrice price = PriceBuilder.createPriceBuilder()
                .setCcyPair("GBP/USD")
                .setBid("1.2500")
                .setAsk("1.2560")
                .setDateTime(LocalDateTime.now())
                .createPrice();
        priceEventHandler.onEvent(new PriceEvent(EventType.PRICE_EVENT, price), 1, false);
        ArgumentCaptor<PriceDto> clientPrice = ArgumentCaptor.forClass(PriceDto.class);
        Mockito.verify(clientPricePublisher).publishPrice(clientPrice.capture());
        PriceDto priceDto = clientPrice.getValue();
        Assert.assertEquals("1.2488", priceDto.getBid());
        Assert.assertEquals("1.2573", priceDto.getAsk());
        Assert.assertEquals(price.getCcyPair(), priceDto.getCcyPair());
    }
}
