package com.gmail.procaro7.gatewayManager.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.gmail.procaro7.gatewayManager.entities.Peripheral;

public interface PeripheralRepository extends CrudRepository<Peripheral, Integer> {
	List<Peripheral> findById(long id);



}
