package com.tanzu.posdataservice.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tanzu.posdataservice.messaging.RabbitMqConfig;
import com.tanzu.posdataservice.model.POSTransaction;

@RestController("POSTransationController")
@RequestMapping("v1/api")
@Profile("sender")
public class POSTransactionController {
	@Autowired
	RabbitTemplate rabbitTemplate;
	
	@Value("${pos.storeId}")
	private String storeId;
	
	Logger logger = LoggerFactory.getLogger(POSTransactionController.class);
	
	@RequestMapping(value="/checkout", method = RequestMethod.POST)
	public void POSCheckout(@RequestBody POSTransaction txn) throws JsonProcessingException {
		
		txn.setStoreId(storeId);
		ObjectMapper mapper = new ObjectMapper();
		rabbitTemplate.convertAndSend(RabbitMqConfig.topicExchangeName,RabbitMqConfig.routingKey,
				mapper.writeValueAsString(txn));
	}

}
