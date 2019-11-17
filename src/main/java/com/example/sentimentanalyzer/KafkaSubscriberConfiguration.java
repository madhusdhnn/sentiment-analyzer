package com.example.sentimentanalyzer;

import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.KafkaListenerContainerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.listener.ConcurrentMessageListenerContainer;

import java.util.HashMap;

import static org.apache.kafka.clients.consumer.ConsumerConfig.*;

@Configuration
public class KafkaSubscriberConfiguration {

    @Value("${spring.kafka.bootstrap-servers}")
    private String bootstrapServers;

    @Value("${spring.kafka.consumer.group-id}")
    private String groupId;

    @Value("${spring.kafka.consumer.auto-offset-reset}")
    private String autoOffsetReset;

    @Bean
    public KafkaListenerContainerFactory<ConcurrentMessageListenerContainer<String, String>> kafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, String> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory());
        return factory;
    }

    @Bean
    public DefaultKafkaConsumerFactory<String, String> consumerFactory() {
        final HashMap<String, Object> consumerConfiguration = new HashMap<String, Object>() {{
            put(BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
            put(KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
            put(VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
            put(GROUP_ID_CONFIG, groupId);
            put(AUTO_OFFSET_RESET_CONFIG, autoOffsetReset);
        }};
        return new DefaultKafkaConsumerFactory<>(consumerConfiguration);
    }

}
