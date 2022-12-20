package com.gmail.procaro7.gatewayManager.controller;

import java.util.List;

public class ErrorResponse
{
    public ErrorResponse(String message, List<String> details, Integer errorNumber) {
        super();
        this.message = message;
        this.details = details;
        this.errorNumber = errorNumber;
    }
  
    private String message;
    private List<String> details;
    private Integer errorNumber;
    
    
	public Integer getErrorNumber() {
		return errorNumber;
	}
	public void setErrorNumber(Integer errorNumber) {
		this.errorNumber = errorNumber;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public List<String> getDetails() {
		return details;
	}
	public void setDetails(List<String> details) {
		this.details = details;
	}
 
  
}