package com.test.ranker;

import com.test.participant.RankedParticipant;
import com.test.participant.ScoredParticipant;

import java.util.List;

public interface Ranker {
    List<RankedParticipant> rank(List<ScoredParticipant> scoredParticipants);
}
