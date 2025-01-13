package com.example.Response;

public class AuthResponse {
	private String jwt;
	private String massage;
	public AuthResponse(String jwt, String massage) {
		super();
		this.jwt = jwt;
		this.massage = massage;
	}
	
	public AuthResponse() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getJwt() {
		return jwt;
	}
	public void setJwt(String jwt) {
		this.jwt = jwt;
	}
	public String getMassage() {
		return massage;
	}
	public void setMassage(String massage) {
		this.massage = massage;
	}
	
}
