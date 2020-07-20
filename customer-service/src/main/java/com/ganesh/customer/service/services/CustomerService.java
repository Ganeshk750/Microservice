package com.ganesh.customer.service.services;

import java.util.List;

import com.ganesh.rentcloud.model.customer.Customer;

public interface CustomerService {
	
	public Customer save(Customer customer);
	
	public Customer findById(int id);
	
	List<Customer> findAll();

}
