package com.ganesh.rent.service.controller.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ganesh.rentcloud.model.rent.Rent;

@Repository
public interface RentRepository extends JpaRepository<Rent, Integer> {

}
