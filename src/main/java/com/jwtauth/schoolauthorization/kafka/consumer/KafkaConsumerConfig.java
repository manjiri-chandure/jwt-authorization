package com.jwtauth.schoolauthorization.kafka.consumer;
import com.jwtauth.schoolauthorization.dto.StudentCreationDto;
import com.jwtauth.schoolauthorization.dto.StudentCreationDtoByKafka;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.security.oauthbearer.internals.secured.ValidateException;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.listener.ContainerProperties;
import org.springframework.kafka.listener.DefaultErrorHandler;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.util.backoff.BackOff;
import org.springframework.util.backoff.FixedBackOff;
import org.springframework.web.client.HttpServerErrorException;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaConsumerConfig {

    @Bean
    public ConsumerFactory<String, StudentCreationDtoByKafka> consumerFactory() {
        Map<String, Object> configProps = new HashMap<>();
        configProps.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        configProps.put(ConsumerConfig.GROUP_ID_CONFIG, "student");
        return new DefaultKafkaConsumerFactory<>(configProps, new StringDeserializer(),
          new JsonDeserializer<>(StudentCreationDtoByKafka.class, false));
    }


//    private Long interval = 10000L;
//
//
//    private Long maxAttempts = 10L;
//
//
//    @Bean
//    public DefaultErrorHandler errorHandler() {
//        BackOff fixedBackOff = new FixedBackOff(interval, maxAttempts);
//        DefaultErrorHandler errorHandler = new DefaultErrorHandler((consumerRecord, exception) -> {
//            // logic to execute when all the retry attemps are exhausted
//            System.out.println("All retry done");
//        }, fixedBackOff);
//        errorHandler.addRetryableExceptions(HttpServerErrorException.class);
//        errorHandler.addNotRetryableExceptions(ValidateException.class);
//        return errorHandler;
//
//    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, StudentCreationDtoByKafka> kafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, StudentCreationDtoByKafka> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory());
//        factory.setCommonErrorHandler(errorHandler());
//        factory.getContainerProperties().setAckMode(ContainerProperties.AckMode.RECORD);

        return factory;
    }

}
