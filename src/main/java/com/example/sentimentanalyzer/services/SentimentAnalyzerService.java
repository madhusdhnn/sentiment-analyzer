package com.example.sentimentanalyzer.services;

import com.example.sentimentanalyzer.models.Sentiment;
import com.example.sentimentanalyzer.models.SentimentType;
import com.example.sentimentanalyzer.models.TweetData;
import com.example.sentimentanalyzer.models.User;
import com.example.sentimentanalyzer.publishers.KafkaPublisher;
import edu.stanford.nlp.neural.rnn.RNNCoreAnnotations;
import edu.stanford.nlp.pipeline.CoreDocument;
import edu.stanford.nlp.pipeline.CoreSentence;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import org.apache.commons.lang3.StringUtils;
import org.intellij.lang.annotations.Language;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SentimentAnalyzerService {

    private final StanfordCoreNLP stanfordCoreNLP;

    private final KafkaPublisher kafkaPublisher;

    @Language("RegExp")
    private static final String CLEAN_TWEET_REGEX = "(@[A-Za-z0-9_]+)|([^0-9A-Za-z \\t])|(\\w+://\\S+)";

    @Autowired
    public SentimentAnalyzerService(StanfordCoreNLP stanfordCoreNLP, KafkaPublisher kafkaPublisher) {
        this.stanfordCoreNLP = stanfordCoreNLP;
        this.kafkaPublisher = kafkaPublisher;
    }

    public void computeSentiment(TweetData rawTweetData) {
        String text = rawTweetData.getText();
        String cleanedTweetText = cleanTweet(text);
        if (StringUtils.isNotBlank(cleanedTweetText)) {
            CoreDocument document = new CoreDocument(cleanedTweetText);
            this.stanfordCoreNLP.annotate(document);
            SentimentType sentimentType = findSentiment(document.sentences());
            User user = rawTweetData.getUser();
            Sentiment sentiment = new Sentiment(rawTweetData.getId(), user.getId(), user.getName(), text, sentimentType.getPolarity(), sentimentType.getSentimentName());
            this.kafkaPublisher.publishAsync(sentiment);
        }
    }

    private SentimentType findSentiment(List<CoreSentence> sentences) {
        int polarityOfTweet = 0;
        int longest = 0;
        for (CoreSentence sentence : sentences) {
            int polarity = RNNCoreAnnotations.getPredictedClass(sentence.sentimentTree());
            String sentenceString = sentence.toString();
            int sentenceStringLength = sentenceString.length();
            if (sentenceStringLength > longest) {
                polarityOfTweet = polarity;
                longest = sentenceStringLength;
            }
        }
        return SentimentType.fromPolarity(polarityOfTweet);
    }

    private String cleanTweet(String tweetData) {
        String lowerCaseTweet = tweetData.toLowerCase();
        return lowerCaseTweet.replaceAll(CLEAN_TWEET_REGEX, " ");
    }

}
