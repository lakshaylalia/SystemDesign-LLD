package events;

public class ReputationEvent {
    private EventType eventType;
    private Long userId;
    private Long points;

    public ReputationEvent(EventType eventType, Long userId, Long points) {
        this.eventType = eventType;
        this.userId = userId;
        this.points = points;
    }

    public EventType getEventType() {
        return eventType;
    }

    public Long getUserId() {
        return userId;
    }

    public Long getPoints() {
        return points;
    }

    @Override
    public String toString() {
        return "ReputationEvent{" +
                "eventType=" + eventType +
                ", userId=" + userId +
                ", points=" + points +
                '}';
    }
}