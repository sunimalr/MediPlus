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

public class ProfileLayout extends Activity{
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		//if(CurrentUser.getCurrentUser().isFirstuser())
		setContentView(R.layout.profilemainview);
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
	//	DatabaseUtil dbUtil = new DatabaseUtil(getApplicationContext());
		//dbUtil.open();
		
		/*
		profileName=(TextView)findViewById(R.id.tvProfileName);
		male=(RadioButton)findViewById(R.id.radioMale);
		female=(RadioButton)findViewById(R.id.radioFemale);
		etDOB=(EditText)findViewById(R.id.etDateOfBirth);
		etWeight=(EditText)findViewById(R.id.etWeight);
		etHeight=(EditText)findViewById(R.id.etHeight);
		spBloodGroup=(Spinner)findViewById(R.id.spinnerBloodGroup);
		*/
		profileName=(TextView)findViewById(R.id.tvProfileName);
		tvgender=(TextView)findViewById(R.id.tvprofileviewgender);
		tvdob=(TextView)findViewById(R.id.tvprofileviewdob);
		tvweight=(TextView)findViewById(R.id.tvprofileviewweight);
		tvheight=(TextView)findViewById(R.id.tvprofileviewheight);
		tvblood=(TextView)findViewById(R.id.tvprofileviewblood);
		
		
		
		/*
		curUser1=new User();
		curUser1.setUser("Sunimal Rathnayake");
		curUser1.setType("master");
		curUser1.setGender("male");
		curUser1.setDob("21/01/1990");
		curUser1.setWeight(Float.parseFloat("80.4"));
		curUser1.setHeight(Float.parseFloat("144.2"));
		*/
		
		
		//dbUtil.addProfile(curUser);
		//dbUtil.close();
		//curUser=UserProfileManager.getUserProfileManager().getUserDetails("Sunimal", getApplicationContext());
		//UserProfileManager.getUserProfileManager().init(getApplicationContext(), curUser);
		//UserProfileManager.getUserProfileManager().updateProfile(curUser1, getApplicationContext());
		
		curUser=UserProfileManager.getUserProfileManager().loadMasterProfile(getApplicationContext());
		
		//Toast.makeText(getApplicationContext(), "Got Master Profile", Toast.LENGTH_LONG).show();
		profileName.setText(curUser.getUser());
		tvgender.setText(curUser.getGender());
		tvdob.setText(curUser.getDob());
		tvweight.setText(curUser.getWeight().toString());
		tvheight.setText(curUser.getHeight().toString());
		//tvblood.setText(curUser.get)
		
		/*
		if(curUser.getGender().equalsIgnoreCase("male"))
			male.setChecked(true);
		else
			female.setChecked(true);
		etHeight.setText(curUser.getHeight().toString());
		etDOB.setText(curUser.getDob());
		etWeight.setText(curUser.getWeight().toString());
	*/
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
