package com.gmail.procaro7.gatewayManager.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.gmail.procaro7.gatewayManager.entities.Gateway;
import com.gmail.procaro7.gatewayManager.entities.Peripheral;
import com.gmail.procaro7.gatewayManager.excepcion.BadIpAddressException;
import com.gmail.procaro7.gatewayManager.excepcion.GatewayFullException;
import com.gmail.procaro7.gatewayManager.repository.GatewayRepository;

@CrossOrigin
@RestController
@RequestMapping(path = "/gateway")

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
			String ipAddress) throws Exception {
		// @ResponseBody means the returned String is the response, not a view name
		// @RequestParam means it is a parameter from the GET or POST request

		Gateway _gatewaey = new Gateway(serialNumber, name, ipAddress);
		gatewayRepository.save(_gatewaey);
		return "Saved";

	}

	@PostMapping(path = "/addPeripheral")
	public ResponseEntity<HttpStatus> addPeripheral(@RequestBody Gateway gateway) throws GatewayFullException {
		// public @ResponseBody String addPeripheral(@RequestParam String vendor,
		// @RequestParam String gatewayId) {
		int quantity = gateway.getPeripherals().size();
		if (quantity > 0) {
			Gateway nGateway = getGateway(gateway.getSerialNumber());
			for (Peripheral _peripheral : gateway.getPeripherals()) {
				if (!(_peripheral.getId()>0)) {
					nGateway.addPeripheral(_peripheral.getVendor(),_peripheral.isStatus());
					break;
				}
			}
			
			gatewayRepository.save(nGateway);
			return new ResponseEntity<>(HttpStatus.ACCEPTED);
		}else {
			
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

	}

	@PostMapping(path = "/addPeripheralCurl")
	public ResponseEntity<HttpStatus> addPeripheral(@RequestParam String vendor, @RequestParam String gatewayId)
			throws GatewayFullException {
		Gateway nGateway = getGateway(gatewayId);
		nGateway.addPeripheral(vendor,false);
		gatewayRepository.save(nGateway);
		return new ResponseEntity<>(HttpStatus.ACCEPTED);

	}

	/*
	 * @PutMapping(path = "/addPeripheral/{gatewayId}/{peripheral}") public
	 * ResponseEntity<HttpStatus> addPeripheral(@PathVariable("gatewayId") String
	 * gatewayId,
	 * 
	 * @PathVariable("peripheral") Peripheral peripheral) { // public @ResponseBody
	 * String addPeripheral(@RequestParam String vendor, // @RequestParam String
	 * gatewayId) { try { Gateway nGateway = getGateway(gatewayId);
	 * nGateway.addPeripheral(vendor); gatewayRepository.save(nGateway); return new
	 * ResponseEntity<>(HttpStatus.NO_CONTENT); } catch (Exception e) { return new
	 * ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR); } }
	 */

	@CrossOrigin
	@PostMapping(path = "/updateGateway")
	public String UpdateGateway(@RequestBody Gateway gateway) {

		Gateway _gateway;
		List<Gateway> n = gatewayRepository.findBySerialNumber(gateway.getSerialNumber());
		if (n.size() <= 0) {
			_gateway = new Gateway(gateway);

		} else {
			_gateway = n.get(0);
			_gateway.setName(gateway.getName());
			_gateway.setIpAddress(gateway.getIpAddress());
		}

		gatewayRepository.save(_gateway);
		return "Saved";

	}

	private Gateway getGateway(String gatewayId) {
		return gatewayRepository.findBySerialNumber(gatewayId).get(0);
	}

	@DeleteMapping("/deleteGateway/{gatewayId}")
	public ResponseEntity<HttpStatus> deleteGateway(@PathVariable("gatewayId") String gatewayId) {
		Gateway nGateway = getGateway(gatewayId);
		gatewayRepository.delete(nGateway);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);

	}

	@DeleteMapping("/deletePeripheral/{id}/{gatewayId}")
	public ResponseEntity<HttpStatus> deletePeripheral(@PathVariable("id") long id,
			@PathVariable("gatewayId") String gatewayId) throws Exception {
		
			Gateway nGateway = getGateway(gatewayId);
			nGateway.deletePeripheral(id);
			gatewayRepository.save(nGateway);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}
