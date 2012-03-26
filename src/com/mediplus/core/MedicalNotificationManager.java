package com.mediplus.core;

import java.util.Calendar;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;

import android.content.Context;
import android.content.Intent;

public class MedicalNotificationManager extends Activity{
	
	// get a Calendar object with current time
	public void setAlarmNotification(Context ctx){ 
	Calendar cal = Calendar.getInstance();
	 // add 5 minutes to the calendar object
	 
	 cal.add(Calendar.MINUTE, 1);
	 Intent intent;
	try {
	intent =new Intent("com.mediplus.presentation.CHARTLISTLAYOUT");
		//intent = new Intent(MedicalNotificationManager.this,Class.forName("com.mediplus.core.AlarmReceiver"));
		 intent.putExtra("alarm_message", "O'Doyle Rules!");
		 PendingIntent sender = PendingIntent.getBroadcast(this, 192837, intent, PendingIntent.FLAG_UPDATE_CURRENT);
		 AlarmManager am = (AlarmManager) getSystemService(ALARM_SERVICE);
		 am.set(AlarmManager.RTC_WAKEUP, cal.getTimeInMillis(), sender);
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	 //Intent intent = new Intent(ctx, AlarmReceiver.class);
	
	 // In reality, you would want to have a static variable for the request code instead of 192837
	 
	 // Get the AlarmManager service
	
	}
}
