package com.test.settings;

import com.test.parser.CsvParser;
import com.test.parser.DecathlonParser;
import com.test.ranker.Ranker;
import com.test.ranker.SharedRanker;
import com.test.score.BasicScoreCalculator;
import com.test.score.ScoreCalculator;
import com.test.writer.DecathlonResultWriter;
import com.test.writer.Writer;

public class AppSettings implements Settings {
    public static class Factory {
        public Settings parse(String[] args) {
            return new AppSettings(
                    new CsvParser(args[1]),
                    new BasicScoreCalculator(),
                    new SharedRanker(),
                    new DecathlonResultWriter(args[3]));
        }
    }

    private DecathlonParser parser;
    private ScoreCalculator scoreCalculator;
    private Ranker ranker;
    private Writer writer;

    private AppSettings(DecathlonParser parser, ScoreCalculator scoreCalculator, Ranker ranker, Writer writer) {
        this.parser = parser;
        this.scoreCalculator = scoreCalculator;
        this.ranker = ranker;
        this.writer = writer;
    }

    @Override
    public DecathlonParser getParser() {
        return parser;
    }

    @Override
    public ScoreCalculator getScoreCalculator() {
        return scoreCalculator;
    }

    @Override
    public Ranker getRanker() {
        return ranker;
    }

    @Override
    public Writer getWriter() {
        return writer;
    }

}
