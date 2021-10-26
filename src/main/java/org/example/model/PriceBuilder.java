package org.example.model;

import java.time.LocalDateTime;

public class PriceBuilder {
    private int id;
    private String ccyPair;
    private String bid;
    private String ask;
    private LocalDateTime dateTime;

    public static PriceBuilder createPriceBuilder() {
        return new PriceBuilder();
    }

    public PriceBuilder setId(int id) {
        this.id = id;
        return this;
    }

    public PriceBuilder setCcyPair(String ccyPair) {
        this.ccyPair = ccyPair;
        return this;
    }

    public PriceBuilder setBid(String bid) {
        this.bid = bid;
        return this;
    }

    public PriceBuilder setAsk(String ask) {
        this.ask = ask;
        return this;
    }

    public PriceBuilder setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
        return this;
    }

    public Price createPrice() {
        return new Price(id, ccyPair, bid, ask, dateTime);
    }
}