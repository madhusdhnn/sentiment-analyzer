package com.example.sentimentanalyzer.models;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@EqualsAndHashCode
@RequiredArgsConstructor
public class Sentiment {

    private final Long statusId;
    private final Long userId;
    private final String username;
    private final String tweetText;
    private final int polarity;
    private final String sentimentName;

}
