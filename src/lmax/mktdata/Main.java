package lmax.mktdata;

import com.lmax.disruptor.BusySpinWaitStrategy;
import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.ProducerType;
import com.lmax.disruptor.util.DaemonThreadFactory;
import lmax.mktdata.event.EventWrapper;
import lmax.mktdata.event.BidAskEvent;
import lmax.mktdata.event.LastCloseEvent;
import lmax.mktdata.handler.ReciverEventHandler;
import lmax.mktdata.handler.LastCloseEventEventHandler;
import lmax.mktdata.handler.BidAskEventEventHandler;


import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * Starts the lmax-example application.
 */
public class Main {

    public static void main(String... args) throws Exception {

        Disruptor<EventWrapper> disruptor = new Disruptor<EventWrapper>(EventWrapper.EVENT_FACTORY,
                1024, DaemonThreadFactory.INSTANCE, ProducerType.SINGLE, new BusySpinWaitStrategy());

        disruptor.handleEventsWith(
                new ReciverEventHandler(), //Thread Subscribing to All events
                new LastCloseEventEventHandler(),//Thread Subscribing to Last Close events
                new ReciverEventHandler(),
                new BidAskEventEventHandler() ////Thread Subscribing to BidAsk events
                );

        RingBuffer<EventWrapper> ringBuffer = disruptor.start();

        for (int i = 1; i <= 100; i++) {
            long seqId = ringBuffer.next();

            EventWrapper eventWrapper = ringBuffer.get(seqId);

            if (i % 2 == 0) {
                String content = generateRandomTicker()+" "+genrateRandomPrice() + " " + genrateRandomPrice();
                BidAskEvent event = new BidAskEvent(content  + " Seq No."+  i);
                eventWrapper.setType(event.getType());
                eventWrapper.setEvent(event);
            } else {
                String content = generateRandomTicker()+" "+genrateRandomPrice();
                LastCloseEvent event = new LastCloseEvent(content  + " Seq No."+  i);
                eventWrapper.setType(event.getType());
                eventWrapper.setEvent(event);
            }

            ringBuffer.publish(seqId);
        }

        Thread.currentThread().join();
    }

    public static String generateRandomTicker(){
        List<String> list = Arrays.asList("PCCW","JP","CLP","SWIRE","FAIRWOOD");
        Collections.shuffle(list);
        String random = list.get(0);

        return random;
    }

    public static double genrateRandomPrice(){
        Random r = new Random();
        int low = 10;
        int high = 100;
        int result = r.nextInt(high-low) + low;
        return (double)result;
    }
}
