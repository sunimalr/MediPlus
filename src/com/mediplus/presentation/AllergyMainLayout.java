package com.mediplus.presentation;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class AllergyMainLayout extends Activity {

	Button btGetAllergyList, btAddnewAllergy;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.allergymainlayout);
		this.setEventListeners();
	}

	private void setEventListeners() {

		btGetAllergyList = (Button) findViewById(R.id.btGetAllergyList);
		btAddnewAllergy = (Button) findViewById(R.id.btAddNewAllergy);
		
		

		btGetAllergyList.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent i = new Intent("com.mediplus.presentation.ALLERGYLIST");
				startActivity(i);

			}
		});

		btAddnewAllergy.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent i2 = new Intent("com.mediplus.presentation.ALLERGY");
				startActivity(i2);

			}
		});

	}

}
