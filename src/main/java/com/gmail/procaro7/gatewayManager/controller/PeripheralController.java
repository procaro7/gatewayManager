package com.gmail.procaro7.gatewayManager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.gmail.procaro7.gatewayManager.entities.Peripheral;
import com.gmail.procaro7.gatewayManager.repository.PeripheralRepository;

@RestController
@RequestMapping(path = "/peripheral")
@CrossOrigin
public class PeripheralController {
	@Autowired
	private PeripheralRepository peripheralRepository;

	@GetMapping(path = "/all")
	public @ResponseBody Iterable<Peripheral> getAllPeripherals() {
		// This returns a JSON or XML with the users
		return peripheralRepository.findAll();
	}

	@PostMapping(path = "/add")
	public @ResponseBody String addPeripheral(@RequestParam String vendor) {
		try {
			Peripheral per = new Peripheral();
			peripheralRepository.save(per);

		} catch (Exception e) {
			return "Failed";
		}
		return "Saved";
	}

	@CrossOrigin
	@PostMapping(path = "/updatePeripheral")
	public ResponseEntity<HttpStatus> updatePeripheral(@RequestBody Peripheral peripheral) {
		try {
			Peripheral storedPeripheral= peripheralRepository.findById(peripheral.getId()).get(0);
			if (storedPeripheral != null) {
			    storedPeripheral.setVendor(peripheral.getVendor());
			    storedPeripheral.setStatus(peripheral.isStatus());
				peripheralRepository.save(storedPeripheral);
			}
			return new ResponseEntity<>(HttpStatus.ACCEPTED);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);

		}
	}

}
