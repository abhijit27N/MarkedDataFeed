package lmax.mktdata.handler;

import com.lmax.disruptor.EventHandler;
import lmax.mktdata.event.EventType;
import lmax.mktdata.event.EventWrapper;
import lmax.mktdata.event.BidAskEvent;
import lmax.mktdata.event.LastCloseEvent;

/**
 * Event handler for both {@link LastCloseEvent} and {@link BidAskEvent}.
 */
public class ReciverEventHandler implements EventHandler<EventWrapper> {

    @Override
    public void onEvent(EventWrapper event, long sequence, boolean endOfBatch) throws Exception {
        if (event.getType() == EventType.LAST_CLOSE.getCode()) {
            System.out.println(String.format("ReciverEventHandler::Last Close Received, %s!", ((LastCloseEvent) event.getEvent()).getName()));
        } else if (event.getType() == EventType.BIDASK.getCode()) {
            System.out.println(String.format("ReciverEventHandler::BID ASK Received, %s!", ((BidAskEvent) event.getEvent()).getName()));
        }
    }
}
