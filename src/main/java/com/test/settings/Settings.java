package com.test.settings;

import com.test.parser.DecathlonParser;
import com.test.ranker.Ranker;
import com.test.score.ScoreCalculator;
import com.test.writer.Writer;

public interface Settings {
    DecathlonParser getParser();
    ScoreCalculator getScoreCalculator();
    Ranker getRanker();
    Writer getWriter();
}
