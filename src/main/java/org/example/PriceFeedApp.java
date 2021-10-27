package org.example;

import org.example.repository.PriceFileRepository;
import org.example.service.PriceFeedService;
import org.example.service.PriceFeedServiceImpl;

/**
 * Price Feed App.
 *
 */
public class PriceFeedApp
{
    public static void main( String[] args )
    {
        PriceFileRepository repository = new PriceFileRepository();
        PriceFeedService service = new PriceFeedServiceImpl(repository);
        service.start();
    }
}
