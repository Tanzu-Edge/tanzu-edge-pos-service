package com.tanzu.posdataservice.model;

import java.math.BigDecimal;
import java.sql.Timestamp;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Entity
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class POSTransaction {
	private Timestamp txnTimestamp;
	private String storeId;
	private POSTransactionItem[] items;
	private BigDecimal netTotal;
	private String transactionId;
	
    @OneToOne(cascade = CascadeType.ALL, targetEntity = CustomerCreditCard.class)
	private CustomerCreditCard ccData;
	
	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;
}
