package com.test.parser;

import com.test.error.AppException;
import com.test.participant.Event;
import com.test.participant.Participant;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CsvParser implements DecathlonParser {
    private String csvFile;

    public CsvParser(String csvFile) {
        this.csvFile = csvFile;
    }

    @Override
    public List<Participant> readParticipants() {
        List<Participant> participants = new ArrayList<>();

        String line = "";
        String cvsSplitBy = ";";

        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            while ((line = br.readLine()) != null) {
                if (line != null && !line.isEmpty()) {
                    participants.add(getParticipant(line.split(cvsSplitBy)));
                }
            }

        } catch (IOException e) {
            throw new AppException("There is an error while parse CSV file", e);
        }

        return Collections.unmodifiableList(participants);
    }

    private Participant getParticipant(String[] params) {
        return Participant.ParticipantBuilder.builder()
                .name(params[0])
                .event(Event.Sprint100m, params[1])
                .event(Event.LongJump, params[2])
                .event(Event.ShotPut, params[3])
                .event(Event.HighJump, params[4])
                .event(Event.Sprint400m, params[5])
                .event(Event.Sprint110mHurdles, params[6])
                .event(Event.DiscusThrow, params[7])
                .event(Event.PoleVault, params[8])
                .event(Event.JavelinThrow, params[9])
                .event(Event.Run1500m, params[10])
                .build();
    }
}
