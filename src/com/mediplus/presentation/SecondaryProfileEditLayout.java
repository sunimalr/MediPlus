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
import android.widget.Toast;

public class SecondaryProfileEditLayout extends Activity {

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
	    
		if (CurrentUser.getCurrentUser().isFirst()) {
		
			Toast.makeText(getApplicationContext(), "Create main profile first..!!", Toast.LENGTH_SHORT).show();
		} else {
			editingUser=new User();
			editingUser.setType("secondary");
			//CurrentUser.getCurrentUser().setCurrentUserType("master");
			Context mContext = getApplicationContext();
			Dialog dialog = new Dialog(mContext);

			dialog.setContentView(R.layout.inputdialog);
			CurrentUser.getCurrentUser().setCtx(getApplicationContext());
			ToastTest.getToastTest().toastTest("Dialogtest");
			dialog.setTitle("Input Dialog");

			
			EditText text=(EditText)dialog.findViewById(R.id.etDialogInput);
			text.setText("Enter Your Name..");
			Button btDialogOK=(Button) dialog.findViewById(R.id.btDialogOK);
			
		}

	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.secondaryprofileedit);
		setup();
		setEventListners();
	}

	private void setEventListners() {

		btSave.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				
				
				editingUser.setUser(etProfileName.getText().toString());
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
				UserProfileManager.getUserProfileManager().init(getApplicationContext(), editingUser);
				
				Intent in=new Intent("com.mediplus.presentation.SECONDARYPROFILELAYOUT");
				startActivity(in);
				finish();
				
			}
		});
	}

}