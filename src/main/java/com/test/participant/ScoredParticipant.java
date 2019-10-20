package com.test.participant;

public class ScoredParticipant implements Comparable<ScoredParticipant> {
    private int totalScore;
    private Participant participant;

    public ScoredParticipant(Participant participant, int totalScore) {
        this.totalScore = totalScore;
        this.participant = participant;
    }

    public int getTotalScore() {
        return totalScore;
    }

    public Participant getParticipant() {
        return participant;
    }

    @Override
    public int compareTo(ScoredParticipant participant) {
        return Integer.compare(participant.totalScore, totalScore);
    }
}
