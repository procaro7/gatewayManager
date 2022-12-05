package com.gmail.procaro7.gatewayManager;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.gmail.procaro7.gatewayManager.entities.Gateway;
import com.gmail.procaro7.gatewayManager.excepcion.BadIpAddressException;

@SpringBootTest
class GatewayManagerApplicationTests {

	private String[] ipList = { "192.168.1.2", "0.0.0.0", "255.255.255.255", "504.0.0.0", "8.8.8.8", "255.4.1.2",
			"152.256.25.14", "192.158.256.3", "192.185.4.256", "152.756.25.14", "192.158.556.3", "192.185.4.656",
			"a.0.0.0", "10.10.10.0", "10.0.0.1" };

	@Test
	void contextLoads() {
	}

	@Test
	void IpAddressTest() {
		int fakeIp = 0;
		Gateway gtw = new Gateway();
		for (String ip : ipList) {
			try {
				gtw.setIpAddress(ip);

			} catch (BadIpAddressException e) {
				fakeIp++;
			}
		}
		assertTrue(fakeIp == 12);
	}
}
