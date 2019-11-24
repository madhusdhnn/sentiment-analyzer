package com.example.sentimentanalyzer;

import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.annotation.EnableKafka;

import java.util.Properties;

@SpringBootApplication
@EnableKafka
public class TwitterSentimentAnalyzerApplication {

    private static final String ANNOTATORS = "tokenize,ssplit,parse,sentiment";

    public static void main(String[] args) {
        SpringApplication.run(TwitterSentimentAnalyzerApplication.class, args);
    }

    @Bean
    public StanfordCoreNLP stanfordCoreNLP() {
        final Properties properties = new Properties();
        properties.setProperty("annotators", ANNOTATORS);
        return new StanfordCoreNLP(properties);
    }

}
