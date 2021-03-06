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
public class AllergyLayout extends Activity {

	Button btAddNewAllergy;
	EditText etAllergy, etSymptoms, etTreatment;
	TextView allergyProfile;
	Allergy curAllergy;
	int index;
	ArrayList<Allergy> curAllergyList;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.allergyedit);
		this.setup();
		this.setEventListeners();
	}

	/*
	 * Initial setup when loading the layout
	 */
	private void setup() {

		etAllergy = (EditText) findViewById(R.id.etAllergyName);
		etSymptoms = (EditText) findViewById(R.id.etAllergySymptoms);
		etTreatment = (EditText) findViewById(R.id.etAllergyMedication);
		allergyProfile = (TextView) findViewById(R.id.tvAllergyProfile);
		btAddNewAllergy=(Button)findViewById(R.id.btAllergyAdd);
		
		curAllergy = new Allergy();
		
		curAllergyList = AllergyManager.getAllergyManager().getAllergyList(
				getApplicationContext(), "Sunimal Rathnayake");
		
		index=0;
		
		/*
		if (curAllergyList.size() > 0) {
			allergyProfile.setText(curAllergyList.get(index).getUser());
			etAllergy.setText(curAllergyList.get(index).getAllergy());
			etSymptoms.setText(curAllergyList.get(index).getSymptoms());
			etTreatment.setText(curAllergyList.get(index).getTreatment());
		}
		else
		
		*/
			Toast.makeText(getApplicationContext(), "Enter Allergy Details Here.", Toast.LENGTH_LONG).show();
	}

	/*
	 * enable text fields
	 */
	public void enableEdit() {

		etAllergy.setEnabled(false);
		etSymptoms.setEnabled(true);

	}

	
	/*
	 * specifies event listeners for the allergy layout
	 */
	private void setEventListeners() {
		
		btAddNewAllergy.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				
				Allergy newAllergy=new Allergy();
				newAllergy.setUser("Sunimal Rathnayake");
				newAllergy.setAllergy(etAllergy.getText().toString());
				newAllergy.setSymptoms(etSymptoms.getText().toString());
				newAllergy.setTreatment(etTreatment.getText().toString());
				Toast.makeText(getApplicationContext(), "New allergy : " + newAllergy.getAllergy()+" saved.", Toast.LENGTH_LONG).show();
				AllergyManager.getAllergyManager().addNewAllergey(newAllergy, getApplicationContext());
								
				finish();

			}
		});

			}

}
