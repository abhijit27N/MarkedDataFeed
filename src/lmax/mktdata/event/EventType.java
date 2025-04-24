package lmax.mktdata.event;

/**
 * Enumeration of all of the supported event types in the application.
 */
public enum EventType {

    BIDASK(1),
    LAST_CLOSE(2);

    private final int code;

    EventType(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}
