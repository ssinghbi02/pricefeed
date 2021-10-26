package org.example.repository;

import com.lmax.disruptor.RingBuffer;
import org.example.disruptor.EventBus;
import org.example.disruptor.PriceEventProducer;
import org.example.event.Event;
import org.example.model.Price;
import org.example.model.PriceBuilder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class PriceFileRepository extends AbstractFileRepository implements PriceRepository {

    private List<Price> prices;
    private EventBus eventBus;

    public PriceFileRepository() {
        super("prices.csv");
    }

    @Override
    public List<Price> getPrices() {
        return prices;
    }

    @Override
    protected void load(InputStream inputStream) {
        prices = new ArrayList<>();
        eventBus = new EventBus();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss:SSS");

        try(InputStreamReader isr = new InputStreamReader(inputStream); BufferedReader reader = new BufferedReader(isr)) {
            String line;
            while ((line = reader.readLine()) != null) {
                final String[] columns = line.split(",");
                if(columns.length != 5) {
                    throw new IllegalStateException("Invalid File");
                }

                Price price = PriceBuilder.createPriceBuilder()
                        .setId(Integer.parseInt(columns[0]))
                        .setCcyPair(columns[1].trim())
                        .setBid(columns[2].trim()) // No need to parse double value, storing as we get from file.
                        .setAsk(columns[3].trim())
                        .setDateTime(LocalDateTime.parse(columns[4], formatter))
                        .createPrice();
                eventBus.publishPrice(price);
                prices.add(price);

            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
