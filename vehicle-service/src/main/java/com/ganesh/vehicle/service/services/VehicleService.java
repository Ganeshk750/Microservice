package com.ganesh.vehicle.service.services;

import java.util.List;

import com.ganesh.rentcloud.model.vehicle.Vehicle;

public interface VehicleService {
	
	Vehicle save (Vehicle vehicle);
	
	Vehicle findById(int id);
	
	List<Vehicle> findAll();

}
