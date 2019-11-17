package com.example.sentimentanalyzer.models;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.DateSerializer;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

@Getter
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class TweetData {

    @JsonSerialize(using = DateSerializer.class)
    private Date createdAt;
    private Long id;
    private String idStr;
    private String text;
    private String source;
    private Boolean truncated;
    private User user;
    private RetweetedStatus retweetedStatus;
    private Boolean isQuoteStatus;
    private Long quoteCount;
    private Long replyCount;
    private Long retweetCount;
    private Long favoriteCount;
    private Boolean favorited;
    private Boolean retweeted;
    private String filterLevel;
    private String lang;
    private String timestampMs;

}
