package com.products.exception;

import java.util.List;

import org.springframework.stereotype.Component;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
@Component
public class ErrorMessage {
	
	private List<String> erroMmessages;
	private String status;
	private long timeStamp;
	
	
	public List<String> getErroMmessages() {
		return erroMmessages;
	}
	public void setErroMmessages(List<String> erroMmessages) {
		this.erroMmessages = erroMmessages;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public long getTimeStamp() {
		return timeStamp;
	}
	public void setTimeStamp(long l) {
		this.timeStamp = l;
	}
	
}
