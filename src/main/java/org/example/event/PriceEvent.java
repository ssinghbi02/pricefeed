package org.example.event;

import org.example.model.InternalPrice;

public class PriceEvent implements Event<InternalPrice> {

    private EventType type;
    private InternalPrice payload;

    public PriceEvent(EventType type) {
        this(type, null);
    }

    public PriceEvent(EventType eventType, InternalPrice payload) {
        this.type = eventType;
        this.payload = payload;
    }

    @Override
    public InternalPrice getPayload() {
        return payload;
    }

    public void setPayload(InternalPrice payload) {
        this.payload = payload;
    }

    @Override
    public EventType getType() {
        return type;
    }
}
