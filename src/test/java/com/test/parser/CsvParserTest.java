package com.test.parser;

import com.test.error.AppException;
import com.test.participant.Event;
import com.test.participant.EventPerformance;
import com.test.participant.Participant;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class CsvParserTest {

    @Rule
    public TemporaryFolder folder = new TemporaryFolder();

    private CsvParser csvParser;

    @Test
    public void readParticipants() throws IOException {
        File tempFolder = folder.newFolder();
        String filename = tempFolder.getAbsolutePath() + "temp.csv";

        List<String> dataLines = new ArrayList<>();
        dataLines.add("John Smith;12.61;5.12;9.23;1.50;60.39;16.43;21.67;2.60;35.81;5.25.72");

        File csvOutputFile = new File(filename);
        try (PrintWriter pw = new PrintWriter(csvOutputFile)) {
            dataLines.forEach(pw::println);
        }
        csvParser = new CsvParser(filename);

        List<Participant> participants = csvParser.readParticipants();
        Participant participant = participants.get(0);

        Assert.assertThat(participant.getName(), is("John Smith"));

        List<EventPerformance> expected = new ArrayList<>();
        expected.add(new EventPerformance(Event.Sprint100m, "12.61"));
        expected.add(new EventPerformance(Event.LongJump, "5.12"));
        expected.add(new EventPerformance(Event.ShotPut, "9.23"));
        expected.add(new EventPerformance(Event.HighJump, "1.50"));
        expected.add(new EventPerformance(Event.Sprint400m, "60.39"));
        expected.add(new EventPerformance(Event.Sprint110mHurdles, "16.43"));
        expected.add(new EventPerformance(Event.DiscusThrow, "21.67"));
        expected.add(new EventPerformance(Event.PoleVault, "2.60"));
        expected.add(new EventPerformance(Event.JavelinThrow, "35.81"));
        expected.add(new EventPerformance(Event.Run1500m, "5.25.72"));

        Assert.assertThat(participant.getEventPerformanceList(), is(expected));
    }

    @Test(expected = AppException.class)
    public void readWrongFile() throws IOException {
        File tempFolder = folder.newFolder();
        String filename = tempFolder.getAbsolutePath() + "temp.csv";

        csvParser = new CsvParser(filename);

        List<Participant> participants = csvParser.readParticipants();
    }
}