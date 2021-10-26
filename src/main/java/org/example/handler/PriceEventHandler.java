package org.example.handler;

import com.lmax.disruptor.EventHandler;
import org.example.event.Event;
import org.example.event.EventType;
import org.example.model.Price;
import org.example.util.RoundingProfile;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class PriceEventHandler implements EventHandler<Event> {
    @Override
    public void onEvent(Event priceEvent, long sequence, boolean endOfBatch) throws Exception {
        if (priceEvent.getType() == EventType.PRICE_EVENT) {
            onPrice((Price)priceEvent.getPayload());
        }
    }

    private void onPrice(Price price) {
        System.out.println("Price Received: " + price);
        applyMargin(price);
    }

    private void applyMargin(Price price) {
        int precison = RoundingProfile.value(price.getCcyPair()).getPrecison();
        BigDecimal bidPrice = new BigDecimal(price.getBid()).multiply(BigDecimal.valueOf(0.999))
                .setScale(precison, RoundingMode.HALF_UP);
        BigDecimal askPrice = new BigDecimal(price.getAsk()).multiply(BigDecimal.valueOf(100.1))
                .setScale(precison, RoundingMode.HALF_UP);
        System.out.println("new bid price After Margin: " + bidPrice.toString());
        System.out.println("new ask price After Margin: " + askPrice.toString());
    }
}
