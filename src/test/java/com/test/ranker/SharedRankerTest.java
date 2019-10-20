package com.test.ranker;

import com.test.participant.Participant;
import com.test.participant.RankedParticipant;
import com.test.participant.ScoredParticipant;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class SharedRankerTest {
    private SharedRanker ranker;

    @Before
    public void setUp() throws Exception {
        ranker = new SharedRanker();
    }

    @Test
    public void rank() {
        Participant participant = Participant.ParticipantBuilder.builder().build();

        List<RankedParticipant> rank = ranker.rank(Arrays.asList(
                new ScoredParticipant(participant, 1000),
                new ScoredParticipant(participant, 500),
                new ScoredParticipant(participant, 2000),
                new ScoredParticipant(participant, 1500),
                new ScoredParticipant(participant, 3000),
                new ScoredParticipant(participant, 1200)
        ));

        Assert.assertThat(
                rank.stream().map(x -> x.getScoredParticipant().getTotalScore()).toArray(Integer[]::new),
                is(new Integer[]{3000, 2000, 1500, 1200, 1000, 500}));

        Assert.assertThat(
                rank.stream().map(RankedParticipant::getRank).toArray(String[]::new),
                is(new String[] {"1", "2", "3", "4", "5", "6"}));
    }

    @Test
    public void sharedRanks() {
        Participant participant = Participant.ParticipantBuilder.builder().build();

        List<RankedParticipant> rank = ranker.rank(Arrays.asList(
                new ScoredParticipant(participant, 1000),
                new ScoredParticipant(participant, 500),
                new ScoredParticipant(participant, 1000),
                new ScoredParticipant(participant, 500),
                new ScoredParticipant(participant, 1000),
                new ScoredParticipant(participant, 1000)
        ));

        Assert.assertThat(
                rank.stream().map(x -> x.getScoredParticipant().getTotalScore()).toArray(Integer[]::new),
                is(new Integer[]{1000, 1000, 1000, 1000, 500, 500}));

        Assert.assertThat(
                rank.stream().map(RankedParticipant::getRank).toArray(String[]::new),
                is(new String[] {"1-4", "1-4", "1-4", "1-4", "5-6", "5-6"}));
    }
}