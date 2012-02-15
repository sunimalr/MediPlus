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

public class AllergyLayout extends Activity {

	Button back, add, delete, next, menu, profile;
	EditText etAllergy, etSymptoms, etTreatment;
	TextView allergyProfile;
	Allergy curAllergy;
	ArrayList<Allergy> curAllergyList;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.allergy);
		this.setup();
		this.setEventListeners();
	}

	private void setup() {

		etAllergy = (EditText) findViewById(R.id.etAllergy);
		etSymptoms = (EditText) findViewById(R.id.etSymptoms);
		etTreatment = (EditText) findViewById(R.id.etMedication);
		allergyProfile = (TextView) findViewById(R.id.tvAllergyProfile);

		curAllergy = new Allergy();
		curAllergyList = AllergyManager.getAllergyManager().getAllergyList(
				getApplicationContext(), "Sunimal");

		if (curAllergyList.size() > 0) {
			allergyProfile.setText(curAllergyList.get(0).getUser());
			etAllergy.setText(curAllergyList.get(0).getAllergy());
			etSymptoms.setText(curAllergyList.get(0).getSymptoms());
			etTreatment.setText(curAllergyList.get(0).getTreatment());
		}
	}

	public void enableEdit() {

		etAllergy.setEnabled(false);
		etSymptoms.setEnabled(true);

	}

	private void setEventListeners() {

		back = (Button) findViewById(R.id.btAllergyBack);
		add = (Button) findViewById(R.id.beAllergyAdd);
		delete = (Button) findViewById(R.id.btAllergyDelete);
		next = (Button) findViewById(R.id.btAllergyNeXt);
		menu = (Button) findViewById(R.id.btAllergyToMenu);
		profile = (Button) findViewById(R.id.btAllergyToProfile);

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
				// TODO Auto-generated method stub

			}
		});

		add.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				curAllergy.setAllergy(etAllergy.getText().toString());
				curAllergy.setSymptoms(etSymptoms.getText().toString());
				curAllergy.setTreatment(etTreatment.getText().toString());
				AllergyManager.getAllergyManager().addNewAllergey(curAllergy,
						getApplicationContext());

			}
		});

		next.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

			}
		});

	}

}
