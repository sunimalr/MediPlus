package com.mediplus.core;

import java.io.File;
import java.io.IOException;

import android.app.Application;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.SharedPreferences;
import android.widget.AnalogClock;

public class CurrentUser {
	private String currentUserName="Sunimal Rathnayake";
	private String currentUserType;
	private String currentUserGender;
	private Context ctx;
	private boolean firstuser=false;
	
	public boolean isFirstuser() {
		return firstuser;
	}

	public void setFirstuser(boolean firstuser) {
		this.firstuser = firstuser;
	}

	File file=new File("reffile");
	
	public Context getCtx() {
		
		return ctx;
	}
	
	public boolean isFirst(){
		if(file.exists()){
			return false;
		}else{
		
			return true;
		}
	}
	

	public void setNotFirstUser(){
		try {
			ContextWrapper cw = new ContextWrapper(CurrentUser.getCurrentUser().getCtx());
			file=cw.getDir("ref", Context.MODE_PRIVATE);
			//file.createNewFile();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void setCtx(Context ctx) {
		this.ctx = ctx;
	}

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
