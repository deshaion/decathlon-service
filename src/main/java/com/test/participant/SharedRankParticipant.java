package com.test.participant;

public class SharedRankParticipant implements RankedParticipant {
    private ScoredParticipant scoredParticipant;
    private int rankFrom;
    private int rankTo;

    public SharedRankParticipant(ScoredParticipant scoredParticipant, int rankFrom, int rankTo) {
        this.scoredParticipant = scoredParticipant;
        this.rankFrom = rankFrom;
        this.rankTo = rankTo;
    }

    @Override
    public String getRank() {
        if (rankFrom == rankTo) {
            return String.valueOf(rankFrom);
        } else {
            return rankFrom + "-" + rankTo;
        }
    }

    @Override
    public ScoredParticipant getScoredParticipant() {
        return scoredParticipant;
    }
}
