package com.example.sentimentanalyzer.publishers;

import com.example.sentimentanalyzer.models.Sentiment;
import com.example.sentimentanalyzer.models.SentimentType;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
@Log4j2
public class KafkaPublisher {

    @Value("${topic.very-positive-tweets.name}")
    private String veryPositiveTopic;

    @Value("${topic.positive-tweets.name}")
    private String positiveTopic;

    @Value("${topic.neutral-tweets.name}")
    private String neutralTopic;

    @Value("${topic.negative-tweets.name}")
    private String negativeTopic;

    @Value("${topic.very-negative-tweets.name}")
    private String veryNegativeTopic;

    private final KafkaTemplate<String, Sentiment> kafkaTemplate;

    @Autowired
    public KafkaPublisher(KafkaTemplate<String, Sentiment> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @Async
    public void publishAsync(Sentiment sentiment) {
        try {
            publish(sentiment);
        } catch (Exception ex) {
            log.error(String.format("Error while publishing to Kafka - %s", ex.getMessage()), ex);
        }
    }

    private void publish(Sentiment data) {
        final String key = String.format("%s_%d", data.getUsername(), data.getUserId());
        switch (SentimentType.fromName(data.getSentimentName())) {
            case VERY_POSITIVE:
                kafkaTemplate.send(veryPositiveTopic, key, data);
                break;
            case POSITIVE:
                kafkaTemplate.send(positiveTopic, key, data);
                break;
            case NEUTRAL:
                kafkaTemplate.send(neutralTopic, key, data);
                break;
            case NEGATIVE:
                kafkaTemplate.send(negativeTopic, key, data);
                break;
            case VERY_NEGATIVE:
                kafkaTemplate.send(veryNegativeTopic, key, data);
                break;
            case DEFAULT:
                log.warn(String.format("Found default sentiment type for user %s. Skipping Kafka publish", data.getUsername()));
                break;
        }
    }

}

