package com.mediplus.presentation;

import test.ToastTest;

import com.mediplus.core.CurrentUser;
import com.mediplus.core.MedicalNotificationManager;
import com.mediplus.core.UserProfileManager;
import com.mediplus.entity.User;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;

public class ProfileEditLayout extends Activity {

	private RadioButton male, female;
	private TextView tvProfileName;
	private EditText etDOB, etWeight, etHeight,etProfileName;
	private Spinner spBloodGroup;
	private Button btSave;
	private User curUser,editingUser;

	private void setup() {

		
		male = (RadioButton) findViewById(R.id.radioMale);
		female = (RadioButton) findViewById(R.id.radioFemale);
		etDOB = (EditText) findViewById(R.id.etDateOfBirthEdit);
		etWeight = (EditText) findViewById(R.id.etWeightEdit);
		etHeight = (EditText) findViewById(R.id.etHeightEdit);
		spBloodGroup = (Spinner) findViewById(R.id.spinnerBloodGroup);
		btSave = (Button) findViewById(R.id.btSaveEdited);
		etProfileName=(EditText)findViewById(R.id.etProfileNameEdit);

		ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.blood_groups, android.R.layout.simple_spinner_item);
	    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
	    spBloodGroup.setAdapter(adapter);
	    
		if (!CurrentUser.getCurrentUser().isFirst()) {
			CurrentUser.getCurrentUser().setCtx(getApplicationContext());
			ToastTest.getToastTest().toastTest("Dialogtest1");
			editingUser=new User();
			curUser=UserProfileManager.getUserProfileManager().loadMasterProfile(getApplicationContext());
			CurrentUser.getCurrentUser().setCurrentUserName(curUser.getUser());
			tvProfileName.setText(CurrentUser.getCurrentUser().getCurrentUserName());
			if (curUser.getGender().equalsIgnoreCase("male")) {
				male.setChecked(true);
				female.setChecked(false);
			} else {
				female.setChecked(true);
				male.setChecked(false);
			}

			etDOB.setText(curUser.getDob());
			etHeight.setText(curUser.getHeight().toString());
			etWeight.setText(curUser.getWeight().toString());
			 //spBloodGroup.set
			  
			 

		} else {
			CurrentUser.getCurrentUser().setNotFirstUser();
			CurrentUser.getCurrentUser().setFirstuser(true);
			editingUser=new User();
			editingUser.setType("master");
			CurrentUser.getCurrentUser().setCurrentUserType("master");
						
		}

	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.profilemainedit);
		setup();
		setEventListners();
	}

	private void setEventListners() {

		btSave.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				
				
				editingUser.setUser(CurrentUser.getCurrentUser().getCurrentUserName());
				editingUser.setDob(etDOB.getText().toString());
				if(male.isChecked()){
				editingUser.setGender("male");
				
				}else{
					editingUser.setGender("female");
				}
				try{
				editingUser.setHeight(Float.parseFloat(etHeight.getText().toString()));
				}catch (NumberFormatException e){
					editingUser.setHeight(Float.parseFloat("0.00"));
					
				}
			try{
				editingUser.setWeight(Float.parseFloat(etWeight.getText().toString()));
			}catch (NumberFormatException e){
				editingUser.setWeight(Float.parseFloat("0.00"));
				
			}
			editingUser.setUser(etProfileName.getText().toString());
				editingUser.setBloodGroup(spBloodGroup.getSelectedItem().toString());
				
				CurrentUser.getCurrentUser().setCtx(getApplicationContext());
				ToastTest.getToastTest().toastTest(editingUser.getBloodGroup());
				
				if(CurrentUser.getCurrentUser().isFirstuser()){
					editingUser.setType("master");
					UserProfileManager.getUserProfileManager().init(getApplicationContext(), editingUser);
				}else {
					UserProfileManager.getUserProfileManager().updateProfile(editingUser, getApplicationContext());
				}
				
				CurrentUser.getCurrentUser().setFirstuser(false);
				
				Intent in=new Intent("com.mediplus.presentation.PROFILE");
				startActivity(in);
				finish();
				
			}
		});
	}

}