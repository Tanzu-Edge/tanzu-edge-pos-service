package com.tanzu.posdataservice;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;

import com.tanzu.posdataservice.model.CustomerCreditCard;
import com.tanzu.posdataservice.model.POSTransaction;
import com.tanzu.posdataservice.model.POSTransactionItem;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@TestPropertySource(properties = "management.metrics.export.wavefront.enabled=false")
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("sender")
class PosDataSenderTests {

	@Test
	void contextLoads() {
	}
	
	@Autowired
	private MockMvc mockMvc;
	
	@Test
	void sendTestMessage() throws JsonProcessingException, Exception {
		POSTransaction posTest = new POSTransaction();
		posTest.setStoreId("roswellEast");
		posTest.setTransactionId("111122223333");
		POSTransactionItem posItemTest = new POSTransactionItem();
		posItemTest.setAmount(BigDecimal.valueOf(10.0));
		posItemTest.setProductId("7");
		posItemTest.setQty(10);
		
		POSTransactionItem[] items = { posItemTest };
		posTest.setItems(items);
		
		CustomerCreditCard ccData = new CustomerCreditCard();
		ccData.setNumber("2222333333");
		posTest.setCcData(ccData);
		
		ObjectMapper mapper = new ObjectMapper();
		
		mockMvc.perform(post("/v1/api/checkout")
			       .contentType(MediaType.APPLICATION_JSON)
			       .content(mapper.writeValueAsString(posTest))
			       .accept(MediaType.APPLICATION_JSON))
			       .andExpect(status().isOk());
		
	}

}
