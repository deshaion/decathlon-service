package com.test.score;

import com.test.participant.Event;
import com.test.participant.Participant;
import com.test.participant.ScoredParticipant;
import org.junit.Assert;
import org.junit.Test;

import java.util.Collections;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class BasicScoreCalculatorTest {

    private BasicScoreCalculator scoreCalculator = new BasicScoreCalculator();

    @Test
    public void testSprint100m() {
        Participant participant = Participant.ParticipantBuilder.builder()
                .name("Test")
                .event(Event.Sprint100m, "10")
                .build();

        List<ScoredParticipant> scoredParticipants = scoreCalculator.calculateScore(Collections.singletonList(participant));
        Assert.assertThat(scoredParticipants.get(0).getTotalScore(), is(1096));
    }

    @Test
    public void testLongJump() {
        Participant participant = Participant.ParticipantBuilder.builder()
                .name("Test")
                .event(Event.LongJump, "5.00")
                .build();

        List<ScoredParticipant> scoredParticipants = scoreCalculator.calculateScore(Collections.singletonList(participant));
        Assert.assertThat(scoredParticipants.get(0).getTotalScore(), is(382));
    }

    @Test
    public void testShotPut() {
        Participant participant = Participant.ParticipantBuilder.builder()
                .name("Test")
                .event(Event.ShotPut, "9.22")
                .build();

        List<ScoredParticipant> scoredParticipants = scoreCalculator.calculateScore(Collections.singletonList(participant));
        Assert.assertThat(scoredParticipants.get(0).getTotalScore(), is(439));
    }

    @Test
    public void testHighJump() {
        Participant participant = Participant.ParticipantBuilder.builder()
                .name("Test")
                .event(Event.HighJump, "2")
                .build();

        List<ScoredParticipant> scoredParticipants = scoreCalculator.calculateScore(Collections.singletonList(participant));
        Assert.assertThat(scoredParticipants.get(0).getTotalScore(), is(803));
    }

    @Test
    public void testSprint400m() {
        Participant participant = Participant.ParticipantBuilder.builder()
                .name("Test")
                .event(Event.Sprint400m, "60.32")
                .build();

        List<ScoredParticipant> scoredParticipants = scoreCalculator.calculateScore(Collections.singletonList(participant));
        Assert.assertThat(scoredParticipants.get(0).getTotalScore(), is(402));
    }

    @Test
    public void testSprint110mHurdles() {
        Participant participant = Participant.ParticipantBuilder.builder()
                .name("Test")
                .event(Event.Sprint110mHurdles, "16.43")
                .build();

        List<ScoredParticipant> scoredParticipants = scoreCalculator.calculateScore(Collections.singletonList(participant));
        Assert.assertThat(scoredParticipants.get(0).getTotalScore(), is(685));
    }

    @Test
    public void testDiscusThrow() {
        Participant participant = Participant.ParticipantBuilder.builder()
                .name("Test")
                .event(Event.DiscusThrow, "21.6")
                .build();

        List<ScoredParticipant> scoredParticipants = scoreCalculator.calculateScore(Collections.singletonList(participant));
        Assert.assertThat(scoredParticipants.get(0).getTotalScore(), is(302));
    }

    @Test
    public void testPoleVault() {
        Participant participant = Participant.ParticipantBuilder.builder()
                .name("Test")
                .event(Event.PoleVault, "2.6")
                .build();

        List<ScoredParticipant> scoredParticipants = scoreCalculator.calculateScore(Collections.singletonList(participant));
        Assert.assertThat(scoredParticipants.get(0).getTotalScore(), is(264));
    }

    @Test
    public void testJavelinThrow() {
        Participant participant = Participant.ParticipantBuilder.builder()
                .name("Test")
                .event(Event.JavelinThrow, "35.81")
                .build();

        List<ScoredParticipant> scoredParticipants = scoreCalculator.calculateScore(Collections.singletonList(participant));
        Assert.assertThat(scoredParticipants.get(0).getTotalScore(), is(382));
    }

    @Test
    public void testRun1500m() {
        Participant participant = Participant.ParticipantBuilder.builder()
                .name("Test")
                .event(Event.Run1500m, "5.25.72")
                .build();

        List<ScoredParticipant> scoredParticipants = scoreCalculator.calculateScore(Collections.singletonList(participant));
        Assert.assertThat(scoredParticipants.get(0).getTotalScore(), is(421));
    }
}