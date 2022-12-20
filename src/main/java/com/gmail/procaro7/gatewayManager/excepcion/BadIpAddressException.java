package com.gmail.procaro7.gatewayManager.excepcion;


public class BadIpAddressException extends RuntimeException {

	public BadIpAddressException(String message) {
		super(message);
	}

	private static final long serialVersionUID = -6414161357595251813L;
   
}
