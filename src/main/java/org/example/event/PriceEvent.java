package org.example.event;

import org.example.model.Price;

public class PriceEvent implements Event<Price> {

    private EventType type;
    private Price payload;

    public PriceEvent(EventType type) {
        this(type, null);
    }

    public PriceEvent(EventType eventType, Price payload) {
        this.type = eventType;
        this.payload = payload;
    }

    @Override
    public Price getPayload() {
        return payload;
    }

    public void setPayload(Price payload) {
        this.payload = payload;
    }

    @Override
    public EventType getType() {
        return type;
    }
}
