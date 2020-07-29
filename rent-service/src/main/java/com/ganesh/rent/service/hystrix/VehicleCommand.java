package com.ganesh.rent.service.hystrix;

import org.springframework.web.client.RestTemplate;

import com.ganesh.rentcloud.model.vehicle.Vehicle;
import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;

public class VehicleCommand extends HystrixCommand<Vehicle> {


    RestTemplate restTemplate;
    int vehicleId;



    public VehicleCommand(RestTemplate restTemplate, int vehicleId){

        super(HystrixCommandGroupKey.Factory.asKey("default"));
        this.restTemplate=restTemplate;
        this.vehicleId=vehicleId;
    }


    @Override
    protected Vehicle run() throws Exception {
        return restTemplate.getForObject("http://vehicle/services/vehicles/"+vehicleId,Vehicle.class);
    }

    @Override
    protected Vehicle getFallback() {
        System.out.println("hit on fallback");
        return new Vehicle();
    }
}
