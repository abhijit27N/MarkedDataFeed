package lmax.mktdata.event;

public class BidAskEvent implements Event {

    private String name;

    public BidAskEvent(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public int getType() {
        return EventType.BIDASK.getCode();
    }
}
