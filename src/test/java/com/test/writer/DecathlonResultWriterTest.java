package com.test.writer;

import com.test.parser.CsvParser;
import com.test.participant.*;
import com.test.ranker.SharedRanker;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class DecathlonResultWriterTest {

    @Rule
    public TemporaryFolder folder = new TemporaryFolder();

    @Test
    public void write() throws IOException {
        File tempFolder = folder.newFolder();
        String filename = tempFolder.getAbsolutePath() + "temp.xml";
        DecathlonResultWriter decathlonResultWriter = new DecathlonResultWriter(filename);

        List<ScoredParticipant> participants = new ArrayList<>();
        participants.add(new ScoredParticipant(
                Participant.ParticipantBuilder.builder().name("Hello").build(), 100));
        participants.add(new ScoredParticipant(
                Participant.ParticipantBuilder.builder().name("World").build(), 200));

        SharedRanker ranker = new SharedRanker();
        decathlonResultWriter.write(ranker.rank(participants));

        try (Stream<String> stream = Files.lines(Paths.get(filename))) {
            String result = stream.collect(Collectors.joining());
            Assert.assertThat(result, is("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?><Participants><Participant><Rank>1</Rank><TotalScore>200</TotalScore><Name>World</Name></Participant><Participant><Rank>2</Rank><TotalScore>100</TotalScore><Name>Hello</Name></Participant></Participants>"));
        }
    }
}