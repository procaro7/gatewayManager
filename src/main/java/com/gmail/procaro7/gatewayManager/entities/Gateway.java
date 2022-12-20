package com.gmail.procaro7.gatewayManager.entities;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.gmail.procaro7.gatewayManager.excepcion.BadIpAddressException;
import com.gmail.procaro7.gatewayManager.excepcion.GatewayFullException;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;

@Entity
public class Gateway {

	@Id
	@Column(name = "gateway_id")
	private String serialNumber;

	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}

	private String name;
	private String ipAddress;
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "gateway_id", referencedColumnName = "gateway_id")
	private List<Peripheral> peripherals;

	public List<Peripheral> getPeripherals() {
		return peripherals;
	}

	public void setPeripherals(List<Peripheral> peripherals) {
		this.peripherals = peripherals;
	}

	public void addPeripheral(String pVendor) throws GatewayFullException {

		Peripheral peripheral = new Peripheral();
		peripheral.setVendor(pVendor);
		  Timestamp ts=new Timestamp(System.currentTimeMillis());  
          Date date=new Date(ts.getTime());  
		//Date date = LocalDateTime.now().
		peripheral.setCreated();

		List<Peripheral> peripherals = this.getPeripherals();
		if (peripherals == null)
			peripherals = new ArrayList<Peripheral>();
		if (peripherals.size() == 10)
			throw new GatewayFullException();

		peripherals.add(peripheral);
		this.setPeripherals(peripherals);

	}

	public Gateway(String serialNumber, String name, String ipAddString) throws BadIpAddressException {
		this.serialNumber = serialNumber;
		this.name = name;
		setIpAddress(ipAddString);
	}

	public Gateway() {
		// TODO Auto-generated constructor stub
	}

	public Gateway(Gateway gateway) throws BadIpAddressException{
		this.serialNumber = gateway.getSerialNumber();
		this.name = gateway.getName();
		setIpAddress(gateway.getIpAddress());
	}

	// private static final String IPV4_PATTERN3 =
	// "^(([1-9]|[1-9][0-9]|1[0-9][0-9]|2[0-4][0-9]|25[0-4])(\\.(?!$)|$)){4}$";
	private static final String IPV4_PATTERN = "^([1-9]|[1-9][0-9]|1[0-9][0-9]|2[0-4][0-9]|25[0-4])\\.([0-9]|[1-9][0-9]|1[0-9][0-9]|2[0-4][0-9]|25[0-4])\\.([0-9]|[1-9][0-9]|1[0-9][0-9]|2[0-4][0-9]|25[0-4])\\.([1-9]|[1-9][0-9]|1[0-9][0-9]|2[0-4][0-9]|25[0-4])";
	private static final Pattern pattern = Pattern.compile(IPV4_PATTERN);

	public String getSerialNumber() {
		return serialNumber;
	}

	/*
	 * public String getId() { return getSerialNumber(); }
	 */

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIpAddress() {
		return ipAddress;
	}

	public void setIpAddress(String ipAddress) throws BadIpAddressException {
		if (isValidIp(ipAddress)) {
			this.ipAddress = ipAddress;
		} else {
			throw new BadIpAddressException("701-Invalid iP Address");
		}
	}

	private static boolean isValidIp(String ipAddress) {
		Matcher matcher = pattern.matcher(ipAddress);
		return matcher.matches();
	}

	public void deletePeripheral(long id) {
		for (Iterator<Peripheral> iterator = peripherals.iterator(); iterator.hasNext();) {
			Peripheral peripheral = (Peripheral) iterator.next();
			if (peripheral.getId() == id) {
				peripherals.remove(peripheral);
				return;
			}
		}

	}

}
