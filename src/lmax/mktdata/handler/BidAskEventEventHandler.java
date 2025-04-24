package lmax.mktdata.handler;

import com.lmax.disruptor.EventHandler;
import lmax.mktdata.event.EventType;
import lmax.mktdata.event.EventWrapper;
import lmax.mktdata.event.BidAskEvent;
import lmax.mktdata.event.LastCloseEvent;

/**
 * Event handler for {@link BidAskEvent}.
 */
public class BidAskEventEventHandler implements EventHandler<EventWrapper> {

    @Override
    public void onEvent(EventWrapper event, long sequence, boolean endOfBatch) throws Exception {
        if (event.getType() == EventType.BIDASK.getCode()) {
            System.out.println(String.format("BidAskEventEventHandler:BidAsk, %s!", ((BidAskEvent) event.getEvent()).getName()));
        }
    }
}
