package com.ganesh.rent.service.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.ganesh.rent.service.controller.repository.RentRepository;
import com.ganesh.rent.service.model.DetailResponce;
import com.ganesh.rentcloud.model.customer.Customer;
import com.ganesh.rentcloud.model.rent.Rent;
import com.ganesh.rentcloud.model.vehicle.Vehicle;

@Service
public class RentServiceImpl implements RentService{
	
	@Autowired
	private RentRepository rentRepository;
	
	@Bean
	RestTemplate getRestTemplete(RestTemplateBuilder builder) {
		return builder.build();
	}
	
	@Autowired
	private RestTemplate restTemplate;
	
	
	

	@Override
	public Rent save(Rent rent) {
		return rentRepository.save(rent);
	}

	@Override
	public Rent findById(int id) {
		Optional<Rent> rent = rentRepository.findById(id);
		if(rent.isPresent()) {
			return rent.get();
		}else {
			return new Rent();
		}
	}

	@Override
	public List<Rent> findAll() {
		return rentRepository.findAll();
	}

	@Override
	public DetailResponce findDetailResponse(int id) {
		Rent rent = findById(id);
		Customer customer=getCustomer(rent.getCustomerId());
        Vehicle vehicle= getVehicle(rent.getVehicleId());
        return new DetailResponce(rent,customer,vehicle);
	}
	
	
	private Customer getCustomer(int customerId) {
		Customer customer = restTemplate.getForObject("http://localhost:8080/services/customers/"+customerId,Customer.class);
		return customer;
	}
	
	 private Vehicle getVehicle(int vehicleId){

	   Vehicle vehicle = restTemplate.getForObject("http://localhost:9191/services/vehicles/"+vehicleId,Vehicle.class);
       return vehicle;

	  }

}
