package org.example.handler;

import org.example.model.PriceDto;

/**
 * Dummy class publishing to client via Rest api
 */
public class ClientPricePublisher {
    public void publishPrice(PriceDto priceDto) {
        System.out.println("publishing price to client: " + priceDto);
    }
}
