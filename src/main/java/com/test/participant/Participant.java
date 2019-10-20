package com.test.participant;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Participant {
    private String name;
    private List<EventPerformance> eventPerformanceList;

    private Participant(String name, List<EventPerformance> eventPerformanceList) {
        this.name = name;
        this.eventPerformanceList = eventPerformanceList;
    }

    public List<EventPerformance> getEventPerformanceList() {
        return eventPerformanceList;
    }

    public String getName() {
        return name;
    }

    public static final class ParticipantBuilder {
        private String name;
        private List<EventPerformance> eventPerformanceList;

        private ParticipantBuilder() {
            eventPerformanceList = new ArrayList<>();
        }

        public static ParticipantBuilder builder() {
            return new ParticipantBuilder();
        }

        public ParticipantBuilder name(String name) {
            this.name = name;
            return this;
        }

        public ParticipantBuilder event(Event event, String performance) {
            eventPerformanceList.add(new EventPerformance(event, performance));
            return this;
        }

        public Participant build() {
            return new Participant(name, Collections.unmodifiableList(eventPerformanceList));
        }
    }
}
