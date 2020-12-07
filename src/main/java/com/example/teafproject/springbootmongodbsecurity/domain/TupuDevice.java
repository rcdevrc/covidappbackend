package com.example.teafproject.springbootmongodbsecurity.domain;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "tupuDevice")
public class TupuDevice {
	
	private String uid;
	private String sid;
	private String mac;
	private String deviceName;
	private String time;
	private String personId;
	private String name;
	private String temperature;
	private String avatarURL;
	private String description;
	public TupuDevice(String uid, String sid, String mac, String deviceName, String time, String personId, String name,
			String temperature, String avatarURL, String description) {

		this.uid = uid;
		this.sid = sid;
		this.mac = mac;
		this.deviceName = deviceName;
		this.time = time;
		this.personId = personId;
		this.name = name;
		this.temperature = temperature;
		this.avatarURL = avatarURL;
		this.description = description;
	}
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	public String getSid() {
		return sid;
	}
	public void setSid(String sid) {
		this.sid = sid;
	}
	public String getMac() {
		return mac;
	}
	public void setMac(String mac) {
		this.mac = mac;
	}
	public String getDeviceName() {
		return deviceName;
	}
	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getPersonId() {
		return personId;
	}
	public void setPersonId(String personId) {
		this.personId = personId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTemperature() {
		return temperature;
	}
	public void setTemperature(String temperature) {
		this.temperature = temperature;
	}
	public String getAvatarURL() {
		return avatarURL;
	}
	public void setAvatarURL(String avatarURL) {
		this.avatarURL = avatarURL;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}	
}
