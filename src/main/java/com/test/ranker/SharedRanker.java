package com.test.ranker;

import com.test.participant.RankedParticipant;
import com.test.participant.ScoredParticipant;
import com.test.participant.SharedRankParticipant;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SharedRanker implements Ranker {
    @Override
    public List<RankedParticipant> rank(List<ScoredParticipant> scoredParticipants) {
        Collections.sort(scoredParticipants);

        int[] pos = new int[scoredParticipants.size()];
        int[] from = new int[scoredParticipants.size()];
        int[] to = new int[scoredParticipants.size()];
        int id = 0;
        while (id < scoredParticipants.size()) {
            int begin = id;
            pos[begin] = begin;
            from[id] = begin + 1;
            while (id + 1 < scoredParticipants.size() && scoredParticipants.get(id + 1).getTotalScore() == scoredParticipants.get(begin).getTotalScore()) {
                id++;
                pos[id] = begin;
            }
            to[begin] = id + 1;
            id++;
        }

        List<RankedParticipant> rankedParticipants = new ArrayList<>();
        for (int i = 0; i < scoredParticipants.size(); i++) {
            rankedParticipants.add(new SharedRankParticipant(scoredParticipants.get(i), from[pos[i]], to[pos[i]]));
        }
        return rankedParticipants;
    }
}
