package com.test;

import com.test.participant.Participant;
import com.test.participant.RankedParticipant;
import com.test.participant.ScoredParticipant;
import com.test.settings.AppSettings;
import com.test.settings.Settings;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        try {
            Settings settings = new AppSettings.Factory().parse(args);

            List<Participant> participants = settings.getParser().readParticipants();
            List<ScoredParticipant> scoredParticipants = settings.getScoreCalculator().calculateScore(participants);
            List<RankedParticipant> rankedParticipants = settings.getRanker().rank(scoredParticipants);
            settings.getWriter().write(rankedParticipants);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }
}
