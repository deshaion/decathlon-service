package com.test.score;

import com.test.participant.Event;
import com.test.participant.EventPerformance;
import com.test.participant.Participant;
import com.test.participant.ScoredParticipant;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.test.participant.Event.*;

public class BasicScoreCalculator implements ScoreCalculator {
    private Map<Event, List<Double>> calculatorParameters;

    public BasicScoreCalculator() {
        calculatorParameters = new HashMap<>();
        calculatorParameters.put(Sprint100m, Arrays.asList(25.4347, 18., 1.81));
        calculatorParameters.put(LongJump, Arrays.asList(0.14354, 220., 1.4));
        calculatorParameters.put(ShotPut, Arrays.asList(51.39, 1.5, 1.05));
        calculatorParameters.put(HighJump, Arrays.asList(0.8465, 75., 1.42));
        calculatorParameters.put(Sprint400m, Arrays.asList(1.53775, 82., 1.81));
        calculatorParameters.put(Sprint110mHurdles, Arrays.asList(5.74352, 28.5, 1.92));
        calculatorParameters.put(DiscusThrow, Arrays.asList(12.91, 4., 1.1));
        calculatorParameters.put(PoleVault, Arrays.asList(0.2797, 100., 1.35));
        calculatorParameters.put(JavelinThrow, Arrays.asList(10.14, 7., 1.08));
        calculatorParameters.put(Run1500m, Arrays.asList(0.03768, 480., 1.85));
    }

    @Override
    public List<ScoredParticipant> calculateScore(List<Participant> participants) {
        return participants.stream()
                .map(participant -> new ScoredParticipant(participant, calcTotalScore(participant)))
                .collect(Collectors.toList());
    }

    private int calcTotalScore(Participant participant) {
        int totalScore = 0;

        for (EventPerformance eventPerformance : participant.getEventPerformanceList()) {
            List<Double> params = calculatorParameters.get(eventPerformance.getEvent());
            double A = params.get(0);
            double B = params.get(1);
            double C = params.get(2);

            if (eventPerformance.getEvent().isTrackEvent()) {
                totalScore += Math.floor(A * Math.pow(B - eventPerformance.getPerformance(), C));
            } else {
                totalScore += Math.floor(A * Math.pow(eventPerformance.getPerformance() - B, C));
            }
        }

        return totalScore;
    }
}
