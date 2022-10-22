package controller.utility;

public interface IEventListener <T extends IEvent> {
    void handle(T event);
}
