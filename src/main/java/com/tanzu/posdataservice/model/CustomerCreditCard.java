package com.tanzu.posdataservice.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Entity
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class CustomerCreditCard {
	
	private Integer cvc;
	private String expiry;
	private String focus;
	private String name;
	private String number;

	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;
	
}
