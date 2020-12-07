package com.example.teafproject.springbootmongodbsecurity.domain;

public class UpdateContactTraceManually {

	private String fromFirstName;
	private String fromLastName;
	private String fromEmailId;
	private String mobilePhone;
	private String department;
	private String office;
	private String toEmailId;
	private String toFirstName;
	private String toLastName;
	private String comments;
	private String date;
	private String time;
	
	public UpdateContactTraceManually(String fromFirstName, String fromLastName, String fromEmailId, String mobilePhone,
			String department, String office, String toEmailId, String toFirstName, String toLastName, String comments,
			String date, String time) {

		this.fromFirstName = fromFirstName;
		this.fromLastName = fromLastName;
		this.fromEmailId = fromEmailId;
		this.mobilePhone = mobilePhone;
		this.department = department;
		this.office = office;
		this.toEmailId = toEmailId;
		this.toFirstName = toFirstName;
		this.toLastName = toLastName;
		this.comments = comments;
		this.date = date;
		this.time = time;
	}

	public String getFromFirstName() {
		return fromFirstName;
	}

	public void setFromFirstName(String fromFirstName) {
		this.fromFirstName = fromFirstName;
	}

	public String getFromLastName() {
		return fromLastName;
	}

	public void setFromLastName(String fromLastName) {
		this.fromLastName = fromLastName;
	}

	public String getFromEmailId() {
		return fromEmailId;
	}

	public void setFromEmailId(String fromEmailId) {
		this.fromEmailId = fromEmailId;
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

	public String getToEmailId() {
		return toEmailId;
	}

	public void setToEmailId(String toEmailId) {
		this.toEmailId = toEmailId;
	}

	public String getToFirstName() {
		return toFirstName;
	}

	public void setToFirstName(String toFirstName) {
		this.toFirstName = toFirstName;
	}

	public String getToLastName() {
		return toLastName;
	}

	public void setToLastName(String toLastName) {
		this.toLastName = toLastName;
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

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}
	
}
