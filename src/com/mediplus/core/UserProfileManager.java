package com.mediplus.core;

import android.content.Context;
import android.database.Cursor;
import android.widget.Toast;

import com.mediplus.entity.User;
import com.mediplus.persistence.DatabaseUtil;

public class UserProfileManager {

	DatabaseUtil dbUtil;

	private static UserProfileManager instance = null;

	private UserProfileManager() {

	}

	public static UserProfileManager getUserProfileManager() {
		if (instance == null) {
			instance = new UserProfileManager();
		}

		return instance;
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
				tempUser.setDesc(cursor.getString(5));

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

	public void updateProfile(User u, Context ctx) {

		dbUtil = new DatabaseUtil(ctx);
		dbUtil.open();

		dbUtil.updateProfile(u);

		dbUtil.close();

	}

	public void init(Context ctx, User u) {
		dbUtil = new DatabaseUtil(ctx);
		dbUtil.open();
		dbUtil.addProfile(u);
		dbUtil.close();
	}

}
