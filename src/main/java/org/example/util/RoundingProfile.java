package org.example.util;

import java.util.Arrays;
import java.util.stream.Stream;

public enum RoundingProfile {
    EURUSD("EUR/USD", 4),
    EURJPY("EUR/JPY", 2),
    GBPUSD("GBP/USD", 4);
    private String ccyPair;
    private int precison;

    RoundingProfile(String ccyPair, int precision) {
        this.ccyPair = ccyPair;
        this.precison = precision;
    }

    public String getCcyPair() {
        return ccyPair;
    }

    public int getPrecison() {
        return precison;
    }

    public static RoundingProfile value(String ccyPair){
        return Arrays.stream(RoundingProfile.values()).filter(e -> e.getCcyPair().equalsIgnoreCase(ccyPair)).findFirst().orElseThrow(IllegalArgumentException:: new);
    }
}
