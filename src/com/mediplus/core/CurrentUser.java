package com.mediplus.core;

public class CurrentUser {
	private String currentUserName="Sunimal Rathnayake";
	private String currentUserType;
	private String currentUserGender;

	private static CurrentUser instance = null;

	private CurrentUser() {

	}

	public static CurrentUser getCurrentUser() {

		if (instance == null)
			instance = new CurrentUser();

		return instance;

	}
	
	
	
	public String getCurrentUserName() {
		return currentUserName;
	}

	public void setCurrentUserName(String currentUserName) {
		this.currentUserName = currentUserName;
	}

	public String getCurrentUserType() {
		return currentUserType;
	}

	public void setCurrentUserType(String currentUserType) {
		this.currentUserType = currentUserType;
	}

	public String getCurrentUserGender() {
		return currentUserGender;
	}

	public void setCurrentUserGender(String currentUserGender) {
		this.currentUserGender = currentUserGender;
	}

}
