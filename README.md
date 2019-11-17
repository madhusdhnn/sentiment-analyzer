# SENTIMENT ANALYZER

- A micro service which will consume tweets that are published in Apache Kafka by [twitter-hose](https://github.com/madhusdhnn/twitter-hose) microservice.

- Standford's CoreNLP is used for sentiment analysis of tweets. The result of analysis will be published to Kafka topics namely,
"positive" and "negative"

- Final aggregated results can be viewed in **KSQL** or **Kafka Streams**
