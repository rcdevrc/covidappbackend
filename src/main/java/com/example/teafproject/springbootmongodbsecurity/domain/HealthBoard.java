package com.example.teafproject.springbootmongodbsecurity.domain;

public class HealthBoard {

	private String firstName;
	private String lastName;
	private String userPrincipalName;
	private String mobilePhone;
	private String department;
	private String office;
	private String status;
	private String comments;
	private String date;
	public HealthBoard() {};
	public HealthBoard(String firstName, String lastName, String userPrincipalName, String mobilePhone,
			String department, String office, String status, String comments, String date) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.userPrincipalName = userPrincipalName;
		this.mobilePhone = mobilePhone;
		this.department = department;
		this.office = office;
		this.status = status;
		this.comments = comments;
		this.date = date;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getUserPrincipalName() {
		return userPrincipalName;
	}

	public void setUserPrincipalName(String userPrincipalName) {
		this.userPrincipalName = userPrincipalName;
	}

	public String getMobilePhone() {
		return mobilePhone;
	}

	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getOffice() {
		return office;
	}

	public void setOffice(String office) {
		this.office = office;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}
	
}
