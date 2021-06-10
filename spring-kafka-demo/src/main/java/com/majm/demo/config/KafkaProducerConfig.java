package com.majm.demo.config;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.kafka.KafkaAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * 一句话功能简述 </br>
 *
 * @author majunmin
 * @description
 * @datetime 2021-06-09 22:03
 * @since
 */
@Configuration
@AutoConfigureBefore(KafkaAutoConfiguration.class)
@EnableKafka
public class KafkaProducerConfig {

    @Value("${spring.kafka.bootstrap-servers}")
    private String servers;
    @Value("${spring.kafka.producer.retries}")
    private Integer retries;
    @Value("${spring.kafka.producer.batch-size}")
    private Integer batchSize;
    @Value("${spring.kafka.producer.properties.linger.ms}")
    private Integer linger;
    @Value("${spring.kafka.producer.buffer-memory}")
    private Integer bufferMemory;

    public Map<String, Object> producerConfig() {

        HashMap<String, Object> props = new HashMap<>();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, servers);
        props.put(ProducerConfig.RETRIES_CONFIG, retries);
        props.put(ProducerConfig.BATCH_SIZE_CONFIG, batchSize);
        props.put(ProducerConfig.LINGER_MS_CONFIG, linger);
        props.put(ProducerConfig.BUFFER_MEMORY_CONFIG, bufferMemory);
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        return props;
    }

    public ProducerFactory<String, Object> producerFactory() {
        return new DefaultKafkaProducerFactory<>(producerConfig());
    }

    @Bean
    public KafkaTemplate<String, Object> kafkaTemplate() {
        return new KafkaTemplate<>(producerFactory());
    }


}
