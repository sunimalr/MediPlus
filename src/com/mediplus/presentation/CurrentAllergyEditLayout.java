package com.mediplus.presentation;

import java.util.ArrayList;

import com.mediplus.core.AllergyManager;
import com.mediplus.entity.Allergy;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/*
 * Responsible for handling GUI functions of Allergy layout specified in allergy.xml
 */
public class CurrentAllergyEditLayout extends Activity {

	private Button btSaveAllergy;
	private EditText etAllergy, etSymptoms, etTreatment;
	private TextView allergyProfile;
	private Allergy curAllergy;
	int index;
	ArrayList<Allergy> curAllergyList;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.currentallergyedit);
		this.setup();
		this.setEventListeners();
	}

	/*
	 * Initial setup when loading the layout
	 */
	private void setup() {

		etAllergy = (EditText) findViewById(R.id.etAllergyEditName);
		etSymptoms = (EditText) findViewById(R.id.etAllergyEditSymptoms);
		etTreatment = (EditText) findViewById(R.id.etAllergyEditMedication);
		allergyProfile = (TextView) findViewById(R.id.tvAllergyProfile);
		btSaveAllergy=(Button)findViewById(R.id.btAllergySaveChanges);
		
		curAllergy = new Allergy();
		
		curAllergy=AllergyManager.getAllergyManager().getCurrentAllergy();
		
		etAllergy.setText(curAllergy.getAllergy());
		etSymptoms.setText(curAllergy.getSymptoms());
		etTreatment.setText(curAllergy.getTreatment());
		allergyProfile.setText(curAllergy.getUser());
		
		Toast.makeText(getApplicationContext(), "Make Allergy Changes Here.", Toast.LENGTH_LONG).show();
	}

	

	
	/*
	 * specifies event listeners for the allergy layout
	 */
	private void setEventListeners() {
		
		btSaveAllergy.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				
				Allergy newAllergy=new Allergy();
				newAllergy.setUser("Sunimal Rathnayake");
				newAllergy.setAllergy(etAllergy.getText().toString());
				newAllergy.setSymptoms(etSymptoms.getText().toString());
				newAllergy.setTreatment(etTreatment.getText().toString());
				Toast.makeText(getApplicationContext(), "New allergy : " + newAllergy.getAllergy()+" saved.", Toast.LENGTH_LONG).show();
				//AllergyManager.getAllergyManager().addNewAllergey(newAllergy, getApplicationContext());
								
				finish();

			}
		});

			}

}
