package com.ganesh.rent.service.model;

import com.ganesh.rentcloud.model.rent.Rent;

public class SampleResponce implements Response {
	
	private Rent rent;

	public SampleResponce(Rent rent) {
		this.rent = rent;
	}

	public Rent getRent() {
		return rent;
	}

	public void setRent(Rent rent) {
		this.rent = rent;
	}
	
	

}
