package com.gmail.procaro7.gatewayManager.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.gmail.procaro7.gatewayManager.entities.Gateway;

public interface  GatewayRepository  extends CrudRepository<Gateway, Integer>{
	//List<Gateway> findByGatewayId(String serialNumber);
	List<Gateway> findBySerialNumber(String serialNumber);
	 

}
