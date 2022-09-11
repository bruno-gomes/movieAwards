package br.com.gomes.movie.dto;

import java.io.Serializable;

public class StatusDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5914764186366947765L;
	private int statusCode;
	private String statusMessage;
	private boolean successProcess;
	
	public StatusDTO() { }
	
	public StatusDTO(String statusMessage) {
		this.statusMessage = statusMessage;
	}
	public StatusDTO(String statusMessage, boolean sucessProcess) {
		this.statusMessage = statusMessage;
		this.successProcess = sucessProcess;
	}
	
	public int getStatusCode() {
		return statusCode;
	}
	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}
	public String getStatusMessage() {
		return statusMessage;
	}
	public void setStatusMessage(String statusMessage) {
		this.statusMessage = statusMessage;
	}

	public boolean isSuccessProcess() {
		return successProcess;
	}

	public void setSuccessProcess(boolean successProcess) {
		this.successProcess = successProcess;
	}
	
	
}
