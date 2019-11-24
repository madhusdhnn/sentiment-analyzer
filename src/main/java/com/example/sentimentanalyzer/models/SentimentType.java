package com.example.sentimentanalyzer.models;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.stream.Stream;

@Getter
@RequiredArgsConstructor
public enum SentimentType {

    VERY_POSITIVE("Very positive", 4),
    POSITIVE("Positive", 3),
    NEUTRAL("Neutral", 2),
    NEGATIVE("Negative", 1),
    VERY_NEGATIVE("Very negative", 0),
    DEFAULT("No Sentiment", -1);

    private final String sentimentName;
    private final int polarity;

    public static SentimentType fromPolarity(int polarity) {
        return Stream.of(SentimentType.values())
                .filter(sentimentType -> sentimentType.polarity == polarity)
                .findFirst()
                .orElse(DEFAULT);
    }

    public static SentimentType fromName(String sentimentName) {
        return Stream.of(SentimentType.values())
                .filter(sentimentType -> sentimentType.sentimentName.equals(sentimentName))
                .findFirst()
                .orElse(DEFAULT);
    }

}
