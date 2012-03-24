package com.mediplus.presentation;

import com.mediplus.core.CurrentUser;
import com.mediplus.core.UserProfileManager;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class Settings extends Activity{

	Button btDeleteMaster;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
	}
	
	private void setup(){
		
		btDeleteMaster=(Button)findViewById(R.id.btDeleteMaster);
				
	}
	
	private void eventListeners(){
		
		btDeleteMaster.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				//UserProfileManager.getUserProfileManager().deleteProfile("Sunimal Rathnayake");
				
			}
		});
		
	}
	

}
