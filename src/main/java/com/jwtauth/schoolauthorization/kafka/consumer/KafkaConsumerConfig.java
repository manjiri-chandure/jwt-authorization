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
        configProps.put(ConsumerConfig.GROUP_ID_CONFIG, "Students");
        return new DefaultKafkaConsumerFactory<>(configProps, new StringDeserializer(),
          new JsonDeserializer<>(StudentCreationDtoByKafka.class, false));
    }


//    @Value(value = "${kafka.backoff.interval}")
//    private Long interval;
//
//    @Value(value = "${kafka.backoff.max_failure}")
//    private Long maxAttempts;
//
//
//    @Bean
//    public DefaultErrorHandler errorHandler() {
//        BackOff fixedBackOff = new FixedBackOff(interval, maxAttempts);
//        DefaultErrorHandler errorHandler;
//      errorHandler = new DefaultErrorHandler((consumerRecord, exception) -> {
//          System.out.println("All retry tried");
//      }, fixedBackOff);
//      errorHandler.addRetryableExceptions(RuntimeException.class);
//      return errorHandler;
//    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, StudentCreationDtoByKafka> kafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, StudentCreationDtoByKafka> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory());
//        factory.setCommonErrorHandler(errorHandler());

        factory.getContainerProperties().setAckMode(ContainerProperties.AckMode.RECORD);

        return factory;
    }

}
