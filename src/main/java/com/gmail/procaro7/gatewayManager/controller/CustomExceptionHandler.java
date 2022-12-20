package com.gmail.procaro7.gatewayManager.controller;

import java.util.ArrayList;
import java.util.List;


import javax.servlet.http.HttpServletRequest;

import org.springframework.boot.autoconfigure.web.WebProperties.Resources;
import org.springframework.boot.autoconfigure.web.reactive.error.AbstractErrorWebExceptionHandler;
import org.springframework.boot.web.reactive.error.ErrorAttributes;
import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import com.gmail.procaro7.gatewayManager.excepcion.BadIpAddressException;
import com.gmail.procaro7.gatewayManager.excepcion.GatewayFullException;

import jakarta.servlet.RequestDispatcher;

@ControllerAdvice
public class CustomExceptionHandler {
	/*
	 * public CustomExceptionHandler(ErrorAttributes errorAttributes, Resources
	 * resources, ApplicationContext applicationContext) { super(errorAttributes,
	 * resources, applicationContext); // TODO Auto-generated constructor stub }
	 */

	private String INCORRECT_REQUEST = "INCORRECT_REQUEST";
	private String BAD_REQUEST = "BAD_REQUEST";

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(BadIpAddressException.class)
	public ResponseEntity<ErrorResponse> handleadIpAddressException(BadIpAddressException ex) {
		List<String> details = new ArrayList<>();
		details.add(ex.getLocalizedMessage());
		/*HttpStatus status = getStatus(request);
		return new ResponseEntity<>(new MyErrorBody(status.value(), ex.getMessage()),
		status);*/
		Integer errorNumber = 701;
		ErrorResponse error = new ErrorResponse(INCORRECT_REQUEST, details,errorNumber);
		return new ResponseEntity<>(error, HttpStatus.LENGTH_REQUIRED);
	}

	@ExceptionHandler(GatewayFullException.class)
	public final ResponseEntity<ErrorResponse> handleGatewayFullException(GatewayFullException ex, HttpServletRequest request) {
		List<String> details = new ArrayList<>();
		details.add(ex.getLocalizedMessage());
		Integer errorNumber = 702;
		ErrorResponse error = new ErrorResponse(INCORRECT_REQUEST, details,errorNumber);
		return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(Exception.class)
	public final ResponseEntity<ErrorResponse> handleException(Exception ex){
		List<String> details = new ArrayList<>();
		details.add(ex.getLocalizedMessage());
		Integer errorNumber = 500;
		ErrorResponse error = new ErrorResponse("Intenal Server Error", details,errorNumber);
		return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	

	/*private HttpStatus getStatus(HttpServletRequest request) {
		Integer code = (Integer) request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
		HttpStatus status = HttpStatus.resolve(code);
		return (status != null) ? status : HttpStatus.INTERNAL_SERVER_ERROR;
	}*/

	/*
	 * @Override protected RouterFunction<ServerResponse>
	 * getRoutingFunction(ErrorAttributes errorAttributes) { // TODO Auto-generated
	 * method stub return null; }
	 */
}