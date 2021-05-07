package com.tanzu.posdataservice.messaging;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tanzu.posdataservice.model.POSTransaction;
import com.tanzu.posdataservice.repository.POSTransactionsRepository;

import io.micrometer.core.instrument.Metrics;
import io.opentracing.Tracer;

@Component
@Profile("receiver,rabbit")
public class PosReceiver {
	@Autowired
	POSTransactionsRepository transactionsRepos;
	
	Logger logger = LoggerFactory.getLogger(PosReceiver.class);
	
	@Autowired
    private Tracer tracer;
	
	public void receivePosMessage(String message) throws IOException {
	    logger.info("Received <" + message + ">");
	    
	    ObjectMapper objectMapper = new ObjectMapper();
	    
	    POSTransaction txn = objectMapper.readValue(message, POSTransaction.class);
	    
	    transactionsRepos.save(txn);
	    
	    Metrics.counter("receivedStore.purchases", "store.ID", txn.getStoreId()).increment(txn.getNetTotal().doubleValue());
	}

}
