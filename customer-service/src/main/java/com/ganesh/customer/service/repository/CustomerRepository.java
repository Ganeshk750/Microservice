package com.ganesh.customer.service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ganesh.rentcloud.model.customer.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer>{

}
