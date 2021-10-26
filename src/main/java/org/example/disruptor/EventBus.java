package org.example.disruptor;

import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.YieldingWaitStrategy;
import com.lmax.disruptor.dsl.Disruptor;
import org.example.event.Event;
import org.example.handler.ClientPricePublisher;
import org.example.handler.PriceEventHandler;
import org.example.model.InternalPrice;

public class EventBus {

    public static final int RING_BUFFER_SIZE = 16 * 1024;
    private RingBuffer<Event> ringBuffer;
    private Disruptor<Event> disruptor;
    private PriceEventProducer priceEventProducer;
    private static volatile EventBus eventBus;


    private EventBus() {
        PriceEventFactory priceEventFactory = new PriceEventFactory();
        this.ringBuffer = RingBuffer.createSingleProducer(priceEventFactory, RING_BUFFER_SIZE, new YieldingWaitStrategy());
        NamedThreadFactory namedThreadFactory = new NamedThreadFactory("disruptor-event-publisher-thread");
        this.disruptor = new Disruptor(priceEventFactory,
                RING_BUFFER_SIZE,
                namedThreadFactory);
        ClientPricePublisher publisher = new ClientPricePublisher();
        PriceEventHandler priceEventHandler = new PriceEventHandler(publisher);
        disruptor.handleEventsWith(priceEventHandler);
        disruptor.start();
        priceEventProducer = new PriceEventProducer(disruptor.getRingBuffer());

    }

    public static EventBus getInstance(){
        if(eventBus == null) {
            return new EventBus();
        }
        return eventBus;
    }

    public void publishPrice(InternalPrice price) {
        priceEventProducer.onData(price);
    }
}
