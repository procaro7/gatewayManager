package com.gmail.procaro7.gatewayManager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.gmail.procaro7.gatewayManager.entities.Peripheral;
import com.gmail.procaro7.gatewayManager.repository.PeripheralRepository;

@RestController
@RequestMapping(path="/peripheral") 
public class PeripheralController {
	@Autowired 
	private PeripheralRepository peripheralRepository;
	
	@GetMapping(path="/all")
	  public @ResponseBody Iterable<Peripheral> getAllPeripherals() {
	    // This returns a JSON or XML with the users
	    return peripheralRepository.findAll();
	  }
	
	
	
	@PostMapping(path = "/add")
	public @ResponseBody String addPeripheral(@RequestParam String vendor) {
		try {
			Peripheral per = new Peripheral();			
			peripheralRepository.save(per);
			
			
		}catch (Exception e) {
			return "Failed";
		}
		return "Saved"; 
	}
}
