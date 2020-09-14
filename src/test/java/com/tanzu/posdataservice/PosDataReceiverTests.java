package com.tanzu.posdataservice;

import org.junit.jupiter.api.Test;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.rabbit.core.RabbitMessagingTemplate;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;

import com.tanzu.posdataservice.model.CustomerCreditCard;
import com.tanzu.posdataservice.model.POSTransaction;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tanzu.posdataservice.messaging.RabbitMqConfig;

@TestPropertySource(properties = "management.metrics.export.wavefront.enabled=false")
@SpringBootTest
@ActiveProfiles("receiver")
class PosDataReceiverTests {

	@Test
	void contextLoads() {
	}
	
	@Autowired
    private RabbitTemplate rabbitTemplate;
	
	@Test
	void sendTestMessage() throws AmqpException, JsonProcessingException {
		POSTransaction posTest = new POSTransaction();
		posTest.setStoreId("roswellEast");
		posTest.setTransactionId("11122233333");
		CustomerCreditCard ccData = new CustomerCreditCard();
		ccData.setCvc(111);
		ccData.setName("Mike Wright");
		posTest.setCcData(ccData);
		
		ObjectMapper mapper = new ObjectMapper();
		
		rabbitTemplate.convertAndSend(RabbitMqConfig.topicExchangeName,RabbitMqConfig.routingKey, mapper.writeValueAsString(posTest));
	}

}
