package org.example.disruptor;

import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.Sequencer;
import com.lmax.disruptor.YieldingWaitStrategy;
import com.lmax.disruptor.dsl.Disruptor;
import org.example.event.Event;
import org.example.event.PriceEvent;
import org.example.handler.PriceEventHandler;
import org.example.model.Price;

public class EventBus {

    public static final int RING_BUFFER_SIZE = 16 * 1024;
    private RingBuffer<Event> ringBuffer;
    private Disruptor<Event> disruptor;
    private PriceEventProducer priceEventProducer;

    public EventBus() {
        PriceEventFactory priceEventFactory = new PriceEventFactory();
        this.ringBuffer = RingBuffer.createSingleProducer(priceEventFactory, RING_BUFFER_SIZE, new YieldingWaitStrategy());
        NamedThreadFactory namedThreadFactory = new NamedThreadFactory("disruptor-event-publisher-thread");
        this.disruptor = new Disruptor(priceEventFactory,
                RING_BUFFER_SIZE,
                namedThreadFactory);
        PriceEventHandler priceEventHandler = new PriceEventHandler();
        disruptor.handleEventsWith(priceEventHandler);
        disruptor.start();
        priceEventProducer = new PriceEventProducer(disruptor.getRingBuffer());

    }

    public void publishPrice(Price price) {
        priceEventProducer.onData(price);
    }

}
