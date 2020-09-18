package com.cts.bms.jwt.model;

import java.io.Serializable;

public class JwtRequest implements Serializable {

	private static final long serialVersionUID = 5926468583005150707L;

	private String pan;
	private String password;

	//default constructor for JSON Parsing
	public JwtRequest()
	{
	}

	
	public JwtRequest(String pan, String password) {
		super();
		this.pan = pan;
		this.password = password;
	}


	public String getPan() {
		return pan;
	}

	public void setPan(String pan) {
		this.pan = pan;
	}


	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}