package com.mediplus.entity;

public class Reminder {
	
	String user;
	String date;
	String time;
	String details;
	boolean notification;
	boolean alarm;
	
	
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
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
	public String getDetails() {
		return details;
	}
	public void setDetails(String details) {
		this.details = details;
	}
	public boolean isNotification() {
		return notification;
	}
	public void setNotification(boolean notification) {
		this.notification = notification;
	}
	public boolean isAlarm() {
		return alarm;
	}
	public void setAlarm(boolean alarm) {
		this.alarm = alarm;
	}
	
	

}
