package com.ganesh.rentcloud.model.customer;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;


@Entity
@Table(name="customer")
@Data
public class Customer {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private String fristName;
	
	private String lastName;
	
	private String dlNumber;
	
	private String zipcode;
	
	@OneToMany(mappedBy = "customer")
	private List<Loyality> loyalities;

}
