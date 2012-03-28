package com.mediplus.core;

import java.util.ArrayList;

import test.ToastTest;
import android.content.Context;
import android.database.Cursor;
import android.database.CursorIndexOutOfBoundsException;
import android.widget.Toast;

import com.mediplus.entity.User;
import com.mediplus.persistence.DatabaseUtil;

public class UserProfileManager {

	/*
	 * UserProfile manager consists the logic for controlling the profile management.
	 * It connects the GUI front end to the database back end.
	 * this is a singleton class
	 */
	DatabaseUtil dbUtil;

	private static UserProfileManager instance = null;
	
	private String currentSecondaryProfile;

	public String getCurrentSecondaryProfile() {
		return currentSecondaryProfile;
	}

	public void setCurrentSecondaryProfile(String currentSecondaryProfile) {
		this.currentSecondaryProfile = currentSecondaryProfile;
	}

	private UserProfileManager() {

	}

	public static UserProfileManager getUserProfileManager() {
		if (instance == null) {
			instance = new UserProfileManager();
		}

		return instance;
	}
	
public ArrayList<String> getSecondaryProfileList(Context ctx){
	
	ArrayList<String> temp=new ArrayList<String>();
	dbUtil = new DatabaseUtil(ctx);
	dbUtil.open();

	Cursor cursor=dbUtil.fetchSecondaryProfileList();
	if (cursor != null) {
		String str=new String();
		str=cursor.getString(0);
		temp.add(str);
		while (cursor.moveToNext()) {
			str=new String();
			str=cursor.getString(0); 
			temp.add(str);
		}
	} else
		Toast.makeText(ctx, "Cannot load profile", Toast.LENGTH_LONG);

		dbUtil.close();
		return temp;
}
	public User getUserDetails(String profile, Context ctx) {

		dbUtil = new DatabaseUtil(ctx);
		dbUtil.open();

		User tempUser = new User();
		// String name="sun";

		Cursor cursor = dbUtil.fetchProfile(profile);

		tempUser.setUser("na");

		if (cursor != null) {
			while (cursor.moveToNext()) {
				tempUser.setUser(profile);
				tempUser.setGender(cursor.getString(1));
				tempUser.setDob(cursor.getString(2));
				tempUser.setWeight(cursor.getFloat(3));
				tempUser.setHeight(cursor.getFloat(4));
				//tempUser.setDesc(cursor.getString(5));
				tempUser.setType(cursor.getString(6));

			}
		} else
			Toast.makeText(ctx, "Cannot load profile", Toast.LENGTH_LONG);

		dbUtil.close();
		if (tempUser.getUser().equalsIgnoreCase("na")) {
			tempUser.setUser(profile);
			init(ctx, tempUser);

		}

		return tempUser;
	}
	
	public User loadMasterProfile(Context ctx){

		dbUtil = new DatabaseUtil(ctx);
		dbUtil.open();

		User tempUser = new User();
		// String name="sun";

		
		Cursor cursor = dbUtil.fetchMasterProfile();
/*
		tempUser.setUser(cursor.getString(0));
		tempUser.setGender(cursor.getString(1));
		tempUser.setDob(cursor.getString(2));
		tempUser.setWeight(cursor.getFloat(3));
		tempUser.setHeight(cursor.getFloat(4));
		tempUser.setDesc(cursor.getString(5));
		//tempUser.setType(cursor.getString(6));
		Toast.makeText(ctx, "Got Master Profile 1", Toast.LENGTH_LONG).show();

*/		
		tempUser.setUser("na");

		try{
		
		if (cursor != null) {
			//while (cursor.moveToNext()) {
				tempUser.setUser(cursor.getString(0));
				tempUser.setGender(cursor.getString(1));
				tempUser.setDob(cursor.getString(2));
				tempUser.setWeight(cursor.getFloat(3));
				tempUser.setHeight(cursor.getFloat(4));
				tempUser.setDesc(cursor.getString(5));
				//tempUser.setType(cursor.getString(6));
				Toast.makeText(ctx, "Got Master Profile", Toast.LENGTH_LONG).show();

			//}
		} else
			Toast.makeText(ctx, "No Master Profile", Toast.LENGTH_LONG).show();
		}catch (CursorIndexOutOfBoundsException e) {
			
			ToastTest.getToastTest().toastTest("Add Master Profile First");
		}

	
		dbUtil.close();
		/*
		if (tempUser.getUser().equalsIgnoreCase("na")) {
			tempUser.setUser(profile);
			init(ctx, tempUser);

		}*/

		return tempUser;
		
		
	}

	public void updateProfile(User u, Context ctx) {

		dbUtil = new DatabaseUtil(ctx);
		dbUtil.open();

		dbUtil.updateProfile(u);

		dbUtil.close();

	}
	
	public void getMasterProfile(Context ctx) {

		dbUtil = new DatabaseUtil(ctx);
		dbUtil.open();
dbUtil.fetchMasterProfile();


		dbUtil.close();

	}

	public void init(Context ctx, User u) {
		dbUtil = new DatabaseUtil(ctx);
		dbUtil.open();
		dbUtil.addProfile(u);
		dbUtil.close();
	}

}
