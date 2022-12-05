package com.gmail.procaro7.gatewayManager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.gmail.procaro7.gatewayManager.entities.Gateway;
import com.gmail.procaro7.gatewayManager.excepcion.BadIpAddressException;
import com.gmail.procaro7.gatewayManager.repository.GatewayRepository;

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
			String ipAddress) {
		// @ResponseBody means the returned String is the response, not a view name
		// @RequestParam means it is a parameter from the GET or POST request

		try {
			Gateway n = new Gateway(serialNumber, name, ipAddress);
			gatewayRepository.save(n);
			return "Saved";

		} catch (BadIpAddressException e) {
			return "Not Saved--> BadIpAddress";
		}

	}

	@PostMapping(path = "/addPeripheral")
	public @ResponseBody String addPeripheral(@RequestParam String vendor, @RequestParam String gatewayId) {
		try {
			/*
			 * Peripheral peripheral = new Peripheral(); peripheral.setVendor(vendor);
			 * //Date date =
			 * Date.from(LocalDate.atStartOfDay(ZoneId.systemDefault()).toInstant()); Date
			 * date = java.sql.Date.valueOf(LocalDate.now()); peripheral.setCreated(date);
			 * 
			 * Gateway nGateway = gatewayRepository.findBySerialNumber(gatewayId).get(0);
			 * peripheral.setGateway(nGateway); peripheralRepository.save(peripheral);
			 */
			Gateway nGateway = getGateway(gatewayId);
			nGateway.addPeripheral(vendor);
			gatewayRepository.save(nGateway);

		} catch (Exception e) {
			return e.toString();
		}
		return "Saved";
	}

	private Gateway getGateway(String gatewayId) {
		return gatewayRepository.findBySerialNumber(gatewayId).get(0);
	}

	@PostMapping(path = "/deletePeripheral")
	public @ResponseBody String deletePeripheral(@RequestParam long id, @RequestParam String gatewayId) {

		try {
			Gateway nGateway = getGateway(gatewayId);
			nGateway.deletePeripheral(id);
			gatewayRepository.save(nGateway);

		} catch (Exception e) {
			return "Failed";
		}
		return "Deleted";
	}
}
