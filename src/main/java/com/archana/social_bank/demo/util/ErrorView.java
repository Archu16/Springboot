package com.archana.social_bank.demo.util;

public class ErrorView {

	private String status;
	
	public ErrorView() {
		super();
		this.status = "Something went wrong.";
	}
	
	public ErrorView(String status) {
		this.status = status;
	}
	

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
