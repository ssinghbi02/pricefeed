package org.example.handler;

import com.lmax.disruptor.EventHandler;
import org.example.disruptor.EventBus;
import org.example.event.Event;
import org.example.event.EventType;
import org.example.model.InternalPrice;
import org.example.model.PriceBuilder;
import org.example.model.PriceDto;
import org.example.util.RoundingProfile;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class PriceEventHandler implements EventHandler<Event> {
    private ClientPricePublisher clientPricePublisher;

    public PriceEventHandler(ClientPricePublisher clientPricePublisher) {
        this.clientPricePublisher = clientPricePublisher;
    }

    @Override
    public void onEvent(Event priceEvent, long sequence, boolean endOfBatch) throws Exception {
        if (priceEvent.getType() == EventType.PRICE_EVENT) {
            onPrice((InternalPrice) priceEvent.getPayload());
        }
    }

    private void onPrice(InternalPrice price) {
        System.out.println("Price Received: " + price);
        PriceDto priceDto = applyMargin(price);
        publishPriceToRest(priceDto);
    }

    private void publishPriceToRest(PriceDto priceDto) {
        clientPricePublisher.publishPrice(priceDto);
    }

    private PriceDto applyMargin(InternalPrice price) {
        int precison = RoundingProfile.value(price.getCcyPair()).getPrecison();
        BigDecimal bidPrice = new BigDecimal(price.getBid()).multiply(BigDecimal.valueOf(0.999))
                .setScale(precison, RoundingMode.HALF_UP);
        BigDecimal askPrice = new BigDecimal(price.getAsk()).multiply(BigDecimal.valueOf(1.001))
                .setScale(precison, RoundingMode.HALF_UP);
        PriceDto priceDto = new PriceDto(price.getId(), price.getCcyPair(), bidPrice.toString(), askPrice.toString(), price.getDateTime());
        return priceDto;
    }
}
