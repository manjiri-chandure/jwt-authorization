package com.jwtauth.schoolauthorization.kafka.producer;
import com.jwtauth.schoolauthorization.dto.LogDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
public class MessageProducer {

    @Autowired
    private KafkaTemplate<String, LogDto> kafkaTemplate;

    public void sendMessageToTopic(LogDto logInfo) {
         CompletableFuture<SendResult<String, LogDto>> future =  kafkaTemplate.send("logs", logInfo);
         future.whenComplete((result, ex)->{
             if(ex == null){
                 System.out.println("sent message = [" + logInfo + "] with offset=["+ result.getRecordMetadata().offset() + "]");
             }else{
                 System.out.println("Unable to send message = ["+ logInfo + "]due to : "+ ex.getMessage());
             }
         });
    }

}