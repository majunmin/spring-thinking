package com.majm.demo;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.concurrent.ListenableFutureCallback;

import java.util.List;
import java.util.concurrent.TimeUnit;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringKafkaDemoApplication.class)
class SpringKafkaDemoApplicationTests {

    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;

    @Test
    void contextLoads() throws InterruptedException {

        kafkaTemplate.send("topic", "key1", "value").addCallback(new ListenableFutureCallback<SendResult<String, Object>>() {
            @Override
            public void onFailure(Throwable ex) {
                log.error("failed to send message");
            }

            @Override
            public void onSuccess(SendResult<String, Object> result) {
                ProducerRecord<String, Object> record = result.getProducerRecord();
                log.info("topic: [{}], key: [{}], value: [{}], partition: [{}]", record.topic(), record.key(), record.value(), record.partition());
            }
        });


        TimeUnit.SECONDS.sleep(3);

    }

    @KafkaListener(topics = "topic1", containerFactory = "batchFactory")
    public void listenBatch(List<ConsumerRecord<String, Object>> records) {
        batchConsumer(records);
    }

    private void batchConsumer(List<ConsumerRecord<String, Object>> records) {
        records.forEach(this::consumeRecord);
    }

    @KafkaListener(topics = {"topic1", "topic"})
    public void listener1(ConsumerRecord<String, Object> record){
        consumeRecord(record);
    }

    private void consumeRecord(ConsumerRecord<String, Object> record) {
        log.debug("主题:{}, 内容: {}", record.topic(), record.value());
    }

}
