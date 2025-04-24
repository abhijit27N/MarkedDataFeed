package lmax.mktdata.handler;

import com.lmax.disruptor.EventHandler;
import lmax.mktdata.event.EventType;
import lmax.mktdata.event.EventWrapper;
import lmax.mktdata.event.LastCloseEvent;

/**
 * Event handler for {@link LastCloseEvent}.
 */
public class LastCloseEventEventHandler implements EventHandler<EventWrapper> {

    @Override
    public void onEvent(EventWrapper event, long sequence, boolean endOfBatch) throws Exception {
        if (event.getType() == EventType.LAST_CLOSE.getCode()) {
            System.out.println(String.format("LastCloseEventEventHandler:Last Close, %s!", ((LastCloseEvent) event.getEvent()).getName()));
        }
    }
}
