package com.mediplus.presentation;

import java.util.ArrayList;
import java.util.jar.Attributes.Name;

import test.ToastTest;

import com.mediplus.core.CurrentUser;
import com.mediplus.core.UserProfileManager;
import com.mediplus.entity.Allergy;
import com.mediplus.entity.User;

import com.mediplus.persistence.DatabaseUtil;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.DataSetObserver;
import android.os.Bundle;
import android.text.InputFilter.LengthFilter;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class SecondaryProfileLayout extends Activity{
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		//if(CurrentUser.getCurrentUser().isFirstuser())
		setContentView(R.layout.individualsecondaryview);
		CurrentUser.getCurrentUser().setCtx(getApplicationContext());
		this.setup();
		
		eventListeners();
	}
	
	TextView profileName,tvgender,tvdob,tvweight,tvheight,tvblood;
	RadioButton male,female;
	EditText etDOB,etWeight,etHeight;
	Spinner spBloodGroup;
	User curUser,curUser1;
	

	private void setup(){
	
		profileName=(TextView)findViewById(R.id.tvProfileName);
		tvgender=(TextView)findViewById(R.id.tvprofileviewgender);
		tvdob=(TextView)findViewById(R.id.tvprofileviewdob);
		tvweight=(TextView)findViewById(R.id.tvprofileviewweight);
		tvheight=(TextView)findViewById(R.id.tvprofileviewheight);
		tvblood=(TextView)findViewById(R.id.tvprofileviewblood);
			
		curUser=UserProfileManager.getUserProfileManager().loadMasterProfile(getApplicationContext());
		
		profileName.setText(curUser.getUser());
		tvgender.setText(curUser.getGender());
		tvdob.setText(curUser.getDob());
		tvweight.setText(curUser.getWeight().toString());
		tvheight.setText(curUser.getHeight().toString());
		
	}
	
	Button btProfileToMenu,btEditProfile,btMedicalCharts,btViewAllergy,btMedicalHistory,btEvents;

	
	
	private void eventListeners(){
		
		btProfileToMenu=(Button)findViewById(R.id.btProfileToMenu);
		btMedicalCharts=(Button)findViewById(R.id.btProfileviewMedicalCharts);
		btEditProfile=(Button)findViewById(R.id.btEditProfile);
		btViewAllergy=(Button)findViewById(R.id.btProfileviewAllergies);
		btMedicalHistory=(Button)findViewById(R.id.btProfileviewMedicalHistory);
		btEvents=(Button)findViewById(R.id.btProfileviewEvents);
		
		btMedicalCharts.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent medichartmenuintent=new Intent("com.mediplus.presentation.MEDICAL_CHART_MENU");
				startActivity(medichartmenuintent);
				
			}
		});
		
		btEditProfile.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				User u=new User();
				
				u.setUser(profileName.getText().toString());
				
				if(male.isChecked())
					u.setGender("male");
				else
					u.setGender("female");
				
				u.setDob(etDOB.getText().toString());
				u.setHeight(Float.parseFloat(etHeight.getText().toString()));
				u.setWeight(Float.parseFloat(etWeight.getText().toString()));
				UserProfileManager.getUserProfileManager().updateProfile(u, getApplicationContext());
				
				
			}
		});
		
		btViewAllergy.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent allergyIntent=new Intent("com.mediplus.presentation.ALLERGYMAIN");
				startActivity(allergyIntent);
				
			}
		});
		
		
		btMedicalHistory.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent historyIntent=new Intent("com.mediplus.presentation.MEDICAL_HISTORY");
				startActivity(historyIntent);
			}
		});
		
		btEvents.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				
			}
		});
		
		btProfileToMenu.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				finish();
				
			}
		});
		
	}

}
