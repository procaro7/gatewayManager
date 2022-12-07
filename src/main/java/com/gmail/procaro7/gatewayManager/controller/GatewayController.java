package com.gmail.procaro7.gatewayManager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.gmail.procaro7.gatewayManager.entities.Gateway;
import com.gmail.procaro7.gatewayManager.excepcion.BadIpAddressException;
import com.gmail.procaro7.gatewayManager.repository.GatewayRepository;

@RestController
@RequestMapping(path = "/gateway")
@CrossOrigin
public class GatewayController {
	@Autowired
	private GatewayRepository gatewayRepository;

	/*
	 * @Autowired private PeripheralRepository peripheralRepository;
	 */

	@GetMapping(path = "/all")
	public @ResponseBody Iterable<Gateway> getAllGateways() {
		// This returns a JSON or XML with the gateways
		return gatewayRepository.findAll();
	}

	@PostMapping(path = "/add")
	public @ResponseBody String addNewgateway(@RequestParam String serialNumber, @RequestParam String name,
			String ipAddress) {
		// @ResponseBody means the returned String is the response, not a view name
		// @RequestParam means it is a parameter from the GET or POST request

		try {
			Gateway _gatewaey = new Gateway(serialNumber, name, ipAddress);
			gatewayRepository.save(_gatewaey);
			return "Saved";

		} catch (BadIpAddressException e) {
			return "Not Saved--> BadIpAddress";
		}

	}

	@PostMapping(path = "/addPeripheral")
	public ResponseEntity<HttpStatus> addPeripheral(@PathVariable("vendor") String vendor,
			@PathVariable("gatewayId") String gatewayId) {
		// public @ResponseBody String addPeripheral(@RequestParam String vendor,
		// @RequestParam String gatewayId) {
		try {
			Gateway nGateway = getGateway(gatewayId);
			nGateway.addPeripheral(vendor);
			gatewayRepository.save(nGateway);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping(path = "/updateGateway")
	public ResponseEntity<HttpStatus> UpdateGateway(@PathVariable("gateway") Gateway gateway) {
		try {
			gatewayRepository.save(gateway);
			return new ResponseEntity<>(HttpStatus.ACCEPTED);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	private Gateway getGateway(String gatewayId) {
		return gatewayRepository.findBySerialNumber(gatewayId).get(0);
	}

	@DeleteMapping("/deletePeripheral/{id}/{gatewayId}")
	public ResponseEntity<HttpStatus> deletePeripheral(@PathVariable("id") long id,
			@PathVariable("gatewayId") String gatewayId) {
		try {
			Gateway nGateway = getGateway(gatewayId);
			nGateway.deletePeripheral(id);
			gatewayRepository.save(nGateway);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
