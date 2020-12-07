package com.example.teafproject.springbootmongodbsecurity.domain;

import java.util.List;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.stereotype.Component;

@Component
@Document(collection = "broadCast")
public class BroadCast {
	
	private String title;
	private String message;
	private String sender;
	private List<String> emailIds;
	private String createDate;
	private String status;
	
	
	
	public String getCreateDate() {
		return createDate;
	}
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getSender() {
		return sender;
	}
	public void setSender(String sender) {
		this.sender = sender;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public List<String> getEmailIds() {
		return emailIds;
	}
	public void setEmailIds(List<String> emailIds) {
		this.emailIds = emailIds;
	}
	
	
	
	

}
