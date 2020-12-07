package com.example.teafproject.springbootmongodbsecurity.domain;

import java.util.List;

public class ViewTracingResponseBean {
	
	private String fromFirstName;
	private String fromEmailId;
	private String toFirstName;
	private String toEmailId;
	private String office="Chicago";
	private String date;
	
	public String getFromFirstName() {
		return fromFirstName;
	}
	public void setFromFirstName(String fromFirstName) {
		this.fromFirstName = fromFirstName;
	}
	public String getFromEmailId() {
		return fromEmailId;
	}
	public void setFromEmailId(String fromEmailId) {
		this.fromEmailId = fromEmailId;
	}
	public String getToFirstName() {
		return toFirstName;
	}
	public void setToFirstName(String toFirstName) {
		this.toFirstName = toFirstName;
	}
	public String getToEmailId() {
		return toEmailId;
	}
	public void setToEmailId(String toEmailId) {
		this.toEmailId = toEmailId;
	}
	public String getOffice() {
		return office;
	}
	public void setOffice(String office) {
		this.office = office;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	
	

}
