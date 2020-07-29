package com.ganesh.rent.service.controller;

import java.util.List;
import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ganesh.rent.service.model.Response;
import com.ganesh.rent.service.model.SampleResponce;
import com.ganesh.rent.service.services.RentService;
import com.ganesh.rentcloud.model.rent.Rent;

@RestController
@RequestMapping("/services/rents")
public class RentController {
	
	@Autowired
	private RentService rentService;
	
	@PostMapping
	public Rent save(@RequestBody Rent rent) {
		return rentService.save(rent);
	}
	
	
	@GetMapping(value="/{id}")
	public Response getRent(@PathVariable int id, @RequestParam(required= false) String type)throws ExecutionException, InterruptedException {
		
		if(type == null) {
			return new SampleResponce(rentService.findById(id));
		}else {
			return rentService.findDetailResponse(id);
		}
		
	}
	
	
	public List<Rent> getAllResnts(){
		return rentService.findAll();
	}
	

}
