package com.example.teafproject.springbootmongodbsecurity.domain;

import java.util.List;

public class HealthBoardPojo {
	private List<HealthBoard> healthBoardList;
	private Integer count;
	public List<HealthBoard> getHealthBoardList() {
		return healthBoardList;
	}
	public void setHealthBoardList(List<HealthBoard> healthBoardList) {
		this.healthBoardList = healthBoardList;
	}
	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = count;
	}
	
	
	
	

}
