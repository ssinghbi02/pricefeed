package org.example.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class InternalPrice {
    private int id;
    private String ccyPair;
    private String bid;
    private String ask;
    private LocalDateTime dateTime;

    public InternalPrice(int id, String ccyPair, String bid, String ask, LocalDateTime dateTime) {
        this.id = id;
        this.ccyPair = ccyPair;
        this.bid = bid;
        this.ask = ask;
        this.dateTime = dateTime;
    }

    public int getId() {
        return id;
    }

    public String getCcyPair() {
        return ccyPair;
    }

    public String getBid() {
        return bid;
    }

    public String getAsk() {
        return ask;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    @Override
    public String toString() {
        return "Price{" +
                "id=" + id +
                ", ccyPair='" + ccyPair + '\'' +
                ", bid=" + bid +
                ", ask=" + ask +
                ", dateTime=" + dateTime +
                '}';
    }
}
