package com.ganesh.rentcloud.model.vehicle;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name="vechile")
@Data
public class Vehicle {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private String make;
	
	private String model;
	
	private String type;
	
    private Integer year;
    
    private Integer odometerValueOnRegister;
    
    private String color;
}
