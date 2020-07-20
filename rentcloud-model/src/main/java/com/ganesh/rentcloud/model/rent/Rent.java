package com.ganesh.rentcloud.model.rent;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name="rent")
@Data
public class Rent {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer rentId;

	private Integer customerId;
	
	private Integer vehicleId;
	
	private LocalDateTime rentFrom;
	
	private LocalDateTime rentTill;
	
	private Integer currentOdometer;
	
	private String returnLocation;
	
}
