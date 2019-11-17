package com.example.sentimentanalyzer.subscribers;

import com.example.sentimentanalyzer.models.TweetData;
import com.example.sentimentanalyzer.services.SentimentAnalyzerService;
import com.example.sentimentanalyzer.utils.JsonUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@Log4j2
public class KafkaSubscriber {

    private final ObjectMapper objectMapper;

    private final SentimentAnalyzerService sentimentAnalyzerService;

    @Autowired
    public KafkaSubscriber(ObjectMapper objectMapper, SentimentAnalyzerService sentimentAnalyzerService) {
        this.objectMapper = objectMapper;
        this.sentimentAnalyzerService = sentimentAnalyzerService;
    }

    @KafkaListener(topics = "${topic.raw-tweet-topic.name}", groupId = "${spring.kafka.consumer.group-id}")
    public void subscribeRawTweetTopic(String rawTweetJson) {
        TweetData tweetData = JsonUtils.safeParseJSON(objectMapper, rawTweetJson, TweetData.class);
        log.info(String.format("Received new Tweet for user %s with status id %d", tweetData.getUser().getName(), tweetData.getId()));
        sentimentAnalyzerService.analyze(tweetData);
    }
}
