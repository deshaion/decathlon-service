package com.test.participant;

public class EventPerformance {
    private Event event;
    private double performance;
    private String originalPerformance;

    public EventPerformance(Event event, String originalPerformance) {
        this.event = event;
        this.performance = event.getConverter().apply(originalPerformance);
        this.originalPerformance = originalPerformance;
    }

    public Event getEvent() {
        return event;
    }

    public double getPerformance() {
        return performance;
    }

    public String getOriginalPerformance() {
        return originalPerformance;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EventPerformance that = (EventPerformance) o;

        if (Double.compare(that.performance, performance) != 0) return false;
        if (event != that.event) return false;
        return originalPerformance != null ? originalPerformance.equals(that.originalPerformance) : that.originalPerformance == null;
    }
}
