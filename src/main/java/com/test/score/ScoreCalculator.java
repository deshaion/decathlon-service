package com.test.score;

import com.test.participant.Participant;
import com.test.participant.ScoredParticipant;

import java.util.List;

public interface ScoreCalculator {
    List<ScoredParticipant> calculateScore(List<Participant> participant);
}
