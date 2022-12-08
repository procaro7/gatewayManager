package com.gmail.procaro7.gatewayManager.entities;

import java.sql.Timestamp;
import java.util.Date;
import jakarta.annotation.Nullable;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Peripheral {

	@Id
	@jakarta.persistence.GeneratedValue()
	private long id;

	public long getId() {
		return id;
	}

	@ManyToOne
	@JoinColumn(name = "Gateway_id")
	private Gateway gateway;

	private String vendor;

	@Nullable
	private Date created;

	@Nullable
	private boolean status;

	public String getVendor() {
		return vendor;
	}

	public void setVendor(String vendor) {
		this.vendor = vendor;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated() {
		Timestamp ts=new Timestamp(System.currentTimeMillis());  
        Date date=new Date(ts.getTime());  
		this.created = date;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	/*public Gateway getGateway() {
		return gateway;
	}*/

	public void setGateway(Gateway gateway)  {
		this.gateway = gateway;
	}

}
