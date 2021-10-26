package org.example.disruptor;

import com.lmax.disruptor.EventFactory;
import org.example.event.Event;
import org.example.event.EventType;
import org.example.event.PriceEvent;

public class PriceEventFactory implements EventFactory<Event> {
    @Override
    public PriceEvent newInstance() {
        return new PriceEvent(EventType.PRICE_EVENT);
    }
}
