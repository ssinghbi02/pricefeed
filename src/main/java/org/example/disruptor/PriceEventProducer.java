package org.example.disruptor;

import com.lmax.disruptor.RingBuffer;
import org.example.event.Event;
import org.example.event.PriceEvent;
import org.example.model.InternalPrice;

public class PriceEventProducer {
    private final RingBuffer<Event> ringBuffer;

    public PriceEventProducer(RingBuffer<Event> ringBuffer) {
        this.ringBuffer = ringBuffer;
    }

    public void onData(InternalPrice data)
    {
        long sequence = ringBuffer.next();
        try
        {
            PriceEvent event = (PriceEvent) ringBuffer.get(sequence);
            event.setPayload(data);
        }
        finally
        {
            ringBuffer.publish(sequence);
        }
    }
}
