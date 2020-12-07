package com.example.teafproject.springbootmongodbsecurity.domain;

public class FireBase {

	private String emailId;
	private String fireBaseToken;
	private String mobileUniqueId;
	
	
	public FireBase(String emailId, String fireBaseToken, String mobileUniqueId) {
		this.emailId = emailId;
		this.fireBaseToken = fireBaseToken;
		this.mobileUniqueId = mobileUniqueId;
	}


	public String getEmailId() {
		return emailId;
	}


	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}


	public String getFireBaseToken() {
		return fireBaseToken;
	}


	public void setFireBaseToken(String fireBaseToken) {
		this.fireBaseToken = fireBaseToken;
	}


	public String getMobileUniqueId() {
		return mobileUniqueId;
	}


	public void setMobileUniqueId(String mobileUniqueId) {
		this.mobileUniqueId = mobileUniqueId;
	}
}
