# SENTIMENT ANALYZER

1. A micro service which will consume tweets that are published in Apache Kafka by [twitter-hose](https://github.com/madhusdhnn/twitter-hose) microservice, clean it and send for sentiment analysis

2. Stanford's CoreNLP library is used, internally, for sentiment analysis of tweets. The result will be published to Kafka topics namely,
"very positive", "positive", "neutral", "negative" and "very negative"

3. Final aggregated results can be viewed in Apache Kafka's **KSQL** or **Kafka Streams**
