package Utility;

public interface IEventListener <T extends IEvent> {
    void handle(T event);
}
