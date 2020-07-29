package com.ganesh.rent.service.services;

import com.netflix.hystrix.HystrixCommand;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import com.netflix.discovery.converters.Auto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.ganesh.rent.service.controller.repository.RentRepository;
import com.ganesh.rent.service.hystrix.CommonHysctrixCommand;
import com.ganesh.rent.service.hystrix.VehicleCommand;
import com.ganesh.rent.service.model.DetailResponce;
import com.ganesh.rentcloud.model.customer.Customer;
import com.ganesh.rentcloud.model.rent.Rent;
import com.ganesh.rentcloud.model.vehicle.Vehicle;

@Service
public class RentServiceImpl implements RentService{
	
	@Autowired
	private RentRepository rentRepository;
	
	
	@Autowired
	HystrixCommand.Setter setter;
	
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
	public DetailResponce findDetailResponse(int id)throws ExecutionException, InterruptedException {
		Rent rent = findById(id);
		Customer customer=getCustomer(rent.getCustomerId());
        Vehicle vehicle= getVehicle(rent.getVehicleId());
        return new DetailResponce(rent,customer,vehicle);
	}
	
	
	
	
	public DetailResponce findDetailResponsefallback(int id) {
        return new DetailResponce(new Rent(),new Customer(),new Vehicle());
    }
	
	
	//Circuit Breaker implementation
	private Customer getCustomer(int customerId) throws ExecutionException, InterruptedException {

        CommonHysctrixCommand<Customer> customerCommonHysctrixCommand=new CommonHysctrixCommand<Customer>(setter,()->
        {
            return restTemplate.getForObject("http://customer/services/customers/"+customerId,Customer.class);
        },()->{
            return new Customer();
        });

        Future<Customer> customerFuture=customerCommonHysctrixCommand.queue();
        return customerFuture.get();

    }

    private Vehicle getVehicle(int vehicleId){

        VehicleCommand vehicleCommand= new VehicleCommand(restTemplate,vehicleId);
        return vehicleCommand.execute();

      // return restTemplate.getForObject("http://vehicle/services/vehicles/"+vehicleId,Vehicle.class);
	
    }
        
        
        /* service calling dynamic port */
	
//	private Customer getCustomer(int customerId) {
//		//Customer customer = restTemplate.getForObject("http://localhost:8080/services/customers/"+customerId,Customer.class);
//		Customer customer=restTemplate.getForObject("http://customer/services/customers/"+customerId,Customer.class);
//		return customer;
//	}
//	
//	 private Vehicle getVehicle(int vehicleId){
//
//	 //  Vehicle vehicle = restTemplate.getForObject("http://localhost:9191/services/vehicles/"+vehicleId,Vehicle.class);
//		 Vehicle vehicle = restTemplate.getForObject("http://vehicle/services/vehicles/"+vehicleId,Vehicle.class);
//       return vehicle;
//
//	  }
        
        
        

}
