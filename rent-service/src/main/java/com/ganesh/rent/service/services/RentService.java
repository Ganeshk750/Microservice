package com.ganesh.rent.service.services;

import java.util.List;

import com.ganesh.rent.service.model.DetailResponce;
import com.ganesh.rentcloud.model.rent.Rent;

public interface RentService {
	
	public Rent save(Rent rent);
	
	public Rent findById(int id);
	
	List<Rent> findAll();
	
	DetailResponce findDetailResponse(int id);
}
