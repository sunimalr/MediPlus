package com.mediplus.presentation;

import java.util.ArrayList;
import java.util.jar.Attributes.Name;

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
		setContentView(R.layout.profilemain);
		this.setup();
		
		eventListeners();
	}
	
	TextView profileName;
	RadioButton male,female;
	EditText etDOB,etWeight,etHeight;
	Spinner spBloodGroup;
	User curUser;
	

	private void setup(){
	//	DatabaseUtil dbUtil = new DatabaseUtil(getApplicationContext());
		//dbUtil.open();
		
		profileName=(TextView)findViewById(R.id.tvProfileName);
		male=(RadioButton)findViewById(R.id.radioMale);
		female=(RadioButton)findViewById(R.id.radioFemale);
		etDOB=(EditText)findViewById(R.id.etDateOfBirth);
		etWeight=(EditText)findViewById(R.id.etWeight);
		etHeight=(EditText)findViewById(R.id.etHeight);
		spBloodGroup=(Spinner)findViewById(R.id.spinnerBloodGroup);
		
		curUser=new User();
		curUser.setUser("Sunimal");
		//dbUtil.addProfile(curUser);
		//dbUtil.close();
		curUser=UserProfileManager.getUserProfileManager().getUserDetails("Sunimal", getApplicationContext());
		
		profileName.setText(curUser.getUser());
		if(curUser.getGender().equalsIgnoreCase("male"))
			male.setChecked(true);
		else
			female.setChecked(true);
		etHeight.setText(curUser.getHeight().toString());
		etDOB.setText(curUser.getDob());
		etWeight.setText(curUser.getWeight().toString());
	}
	
	Button btProfileToMenu,btEditProfile,btMedicalCharts,btAllergy,btMedicalHistory,btEvents;

	
	
	private void eventListeners(){
		
		btProfileToMenu=(Button)findViewById(R.id.btProfileToMenu);
		btMedicalCharts=(Button)findViewById(R.id.btProfileMedicalCharts);
		btEditProfile=(Button)findViewById(R.id.btEditProfile);
		btAllergy=(Button)findViewById(R.id.btProfileAllergies);
		btMedicalHistory=(Button)findViewById(R.id.btProfileMedicalHistory);
		btEvents=(Button)findViewById(R.id.btProfileEvents);
		
		btMedicalCharts.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
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
		
		btAllergy.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent allergyIntent=new Intent("com.mediplus.presentation.ALLERGY");
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
