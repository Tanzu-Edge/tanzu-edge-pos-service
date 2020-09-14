package com.tanzu.posdataservice.model;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Entity
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class POSTransactionItem implements Serializable {
	private String productId;
	private Integer qty;
	private BigDecimal amount;
	
	@JsonIgnore
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	@ManyToOne public POSTransaction transaction;
	
	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;
}
