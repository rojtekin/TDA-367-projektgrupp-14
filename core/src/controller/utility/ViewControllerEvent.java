package controller.utility;

public class ViewControllerEvent implements IEvent{
    private final EventType eventType;

    public ViewControllerEvent(EventType eventType) {
        this.eventType = eventType;
    }

    public enum EventType {
        VIEW_MAIN_SCREEN,
        VIEW_GAME_SCREEN
    }

    public EventType getEventType() {
        return eventType;
    }

}