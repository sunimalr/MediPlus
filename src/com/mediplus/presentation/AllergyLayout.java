package com.mediplus.presentation;

import java.util.ArrayList;

import com.mediplus.core.AllergyManager;
import com.mediplus.entity.Allergy;

import android.app.Activity;
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

	Button back, add, delete, next, menu, profile;
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
		//this.setEventListeners();
	}

	/*
	 * Initial setup when loading the layout
	 */
	private void setup() {

		etAllergy = (EditText) findViewById(R.id.etAllergyName);
		etSymptoms = (EditText) findViewById(R.id.etAllergySymptoms);
		etTreatment = (EditText) findViewById(R.id.etAllergyMedication);
		allergyProfile = (TextView) findViewById(R.id.tvAllergyProfile);

		curAllergy = new Allergy();
		curAllergyList = AllergyManager.getAllergyManager().getAllergyList(
				getApplicationContext(), "Sunimal Rathnayake");
		
		index=0;
		if (curAllergyList.size() > 0) {
			allergyProfile.setText(curAllergyList.get(index).getUser());
			etAllergy.setText(curAllergyList.get(index).getAllergy());
			etSymptoms.setText(curAllergyList.get(index).getSymptoms());
			etTreatment.setText(curAllergyList.get(index).getTreatment());
		}
		else
			Toast.makeText(getApplicationContext(), "No Allergies for this user..!!", Toast.LENGTH_LONG).show();
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
/*
		back = (Button) findViewById(R.id.btAllergyBack);
		add = (Button) findViewById(R.id.beAllergyAdd);
		delete = (Button) findViewById(R.id.btAllergyDelete);
		next = (Button) findViewById(R.id.btAllergyNeXt);
		menu = (Button) findViewById(R.id.btAllergyToMenu);
		profile = (Button) findViewById(R.id.btAllergyToProfile);
*/
		profile.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				finish();

			}
		});

		menu.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

			}
		});

		delete.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

			}
		});

		back.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if(0<index){
					index--;
					allergyProfile.setText(curAllergyList.get(index).getUser());
					etAllergy.setText(curAllergyList.get(index).getAllergy());
					etSymptoms.setText(curAllergyList.get(index).getSymptoms());
					etTreatment.setText(curAllergyList.get(index).getTreatment());
				}

			}
		});

		add.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				curAllergy.setAllergy(etAllergy.getText().toString());
				curAllergy.setSymptoms(etSymptoms.getText().toString());
				curAllergy.setTreatment(etTreatment.getText().toString());
				curAllergy.setUser("Sunimal");
				AllergyManager.getAllergyManager().addNewAllergey(curAllergy,getApplicationContext());
				curAllergyList = AllergyManager.getAllergyManager().getAllergyList(
						getApplicationContext(), "Sunimal");

			}
		});

		next.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				
				if(curAllergyList.size()-1<index){
					index++;
					allergyProfile.setText(curAllergyList.get(index).getUser());
					etAllergy.setText(curAllergyList.get(index).getAllergy());
					etSymptoms.setText(curAllergyList.get(index).getSymptoms());
					etTreatment.setText(curAllergyList.get(index).getTreatment());
				}
				

			}
		});

	}

}
