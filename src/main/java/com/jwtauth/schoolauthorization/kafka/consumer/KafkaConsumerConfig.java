package com.jwtauth.schoolauthorization.kafka.consumer;

import com.jwtauth.schoolauthorization.dto.StudentCreationDto;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.kafka.support.serializer.JsonSerializer;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaConsumerConfig {

    @Bean
    public ConsumerFactory<String, StudentCreationDto> consumerFactory() {
        Map<String, Object> configProps = new HashMap<>();
        configProps.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        configProps.put(ConsumerConfig.GROUP_ID_CONFIG, "student");
        return new DefaultKafkaConsumerFactory<>(configProps, new StringDeserializer(),
          new JsonDeserializer<>(StudentCreationDto.class, false));
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, StudentCreationDto> kafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, StudentCreationDto> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory());
        return factory;
    }

}
