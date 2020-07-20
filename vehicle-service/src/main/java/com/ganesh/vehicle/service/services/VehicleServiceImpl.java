package com.ganesh.vehicle.service.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ganesh.rentcloud.model.vehicle.Vehicle;
import com.ganesh.vehicle.service.repository.VehicleRepository;

@Service
public class VehicleServiceImpl implements VehicleService {
	
	@Autowired
	private VehicleRepository vehicleRepository;

	@Override
	public Vehicle save(Vehicle vehicle) {
		return vehicleRepository.save(vehicle);
	}

	@Override
	public Vehicle findById(int id) {
		Optional<Vehicle> vehicle = vehicleRepository.findById(id);
		if(vehicle.isPresent()) {
			return vehicle.get();
		}else {
			return new Vehicle();
		}
	}

	@Override
	public List<Vehicle> findAll() {
		return vehicleRepository.findAll();
	}

}
