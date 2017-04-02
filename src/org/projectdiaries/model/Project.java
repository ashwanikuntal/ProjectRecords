package org.projectdiaries.model;

import java.math.BigDecimal;
import java.util.Date;

public class Project {

	private int projectID;
	private String projectName;
	private Date start_time;
	private Date end_time;
	private Date todayDate;
	private BigDecimal rate;
	private BigDecimal wages;
	private String status;
	
	public int getProjectID() {
		return projectID;
	}
	public void setProjectID(int projectID) {
		this.projectID = projectID;
	}
	public String getProjectName() {
		return projectName;
	}
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	public Date getStart_time() {
		return start_time;
	}
	public void setStart_time(Date start_time) {
		this.start_time = start_time;
	}
	public Date getEnd_time() {
		return end_time;
	}
	public void setEnd_time(Date end_time) {
		this.end_time = end_time;
	}
	public Date getTodayDate() {
		return todayDate;
	}
	public void setTodayDate(Date todayDate) {
		this.todayDate = todayDate;
	}
	public BigDecimal getRate() {
		return rate;
	}
	public void setRate(BigDecimal rate) {
		this.rate = rate;
	}
	public BigDecimal getWages() {
		return wages;
	}
	public void setWages(BigDecimal wages) {
		this.wages = wages;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
}
