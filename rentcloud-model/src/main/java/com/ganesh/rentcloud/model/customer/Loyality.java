package com.ganesh.rentcloud.model.customer;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name="loyalityPoint")
@Data
public class Loyality {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer transactionId;
	
	
	@ManyToOne
	@JoinColumn
	private Customer customer;
	
	private int point;
	
	private LocalDateTime expireDate;

}
