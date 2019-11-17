package com.example.sentimentanalyzer.models;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.deser.std.DateDeserializers;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

@Getter
@ToString
@NoArgsConstructor
@EqualsAndHashCode
public class RetweetedStatus {

    @JsonDeserialize(using = DateDeserializers.DateDeserializer.class)
    private Date createdAt;
    private Long id;
    private String idStr;
    private String text;
    private String source;
    private Boolean truncated;
    private User user;
    private Boolean isQuoteStatus;
    private Long quoteCount;
    private Long replyCount;
    private Long retweetCount;
    private Long favoriteCount;
    private Boolean favorited;
    private Boolean retweeted;
    private String filterLevel;
    private String lang;

}
