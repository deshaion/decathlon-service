package com.test.writer;

import com.test.participant.RankedParticipant;

import java.util.List;

public interface Writer {
    void write(List<RankedParticipant> participants);
}
