package com.mediplus.presentation;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class SecondaryProfileMenu extends Activity {

	Button btNew, btExisting;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.secondaryprofilemenu);
		setup();
		setEventListeners();

	}

	private void setup() {

		btExisting = (Button) findViewById(R.id.btViewSecondaryProfileList);

		btNew = (Button) findViewById(R.id.btAddNewSecondaryProfile);

	}

	private void setEventListeners() {

		btNew.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent i=new Intent("com.mediplus.presentation.SECONDARYPROFILEEDITLAYOUT");
				startActivity(i);
				

			}
		});

		btExisting.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent i=new Intent("com.mediplus.presentation.EXISTINGPROFILEMENU");
				startActivity(i);

			}
		});

	}

}
