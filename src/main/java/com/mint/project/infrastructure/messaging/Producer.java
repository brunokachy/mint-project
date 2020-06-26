package com.mint.project.infrastructure.messaging;

import java.util.Properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

/**
 * @author Bruno Okafor 2020-06-26
 */
@RequiredArgsConstructor
@Log4j2
@Service
public class Producer {

	@Value("${kafka.topic}")
	private String topic;

	private final KafkaTemplate<String, String> kafkaTemplate;

	public void sendMessage(CardMessage message) {
		log.info(String.format("#### -> Producing message -> %s", message));
		this.kafkaTemplate.send(topic, message.toString());
	}
}
