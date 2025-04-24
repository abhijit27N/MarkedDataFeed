package lmax.mktdata.event;


public class LastCloseEvent implements Event {

    private String name;

    public LastCloseEvent(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public int getType() {
        return EventType.LAST_CLOSE.getCode();
    }
}
