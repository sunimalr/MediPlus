package com.mediplus.presentation;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;


public class MedicalHistoryLayout extends Activity{
	
	Button exitToProfile;
    @Override
    public void onCreate(Bundle savedInstanceState) {
    	
        super.onCreate(savedInstanceState);

        setContentView(R.layout.medicalhistoryedit);
      
        setEventListeners();
        
    }
    
    private void setEventListeners() {

		
		exitToProfile = (Button) findViewById(R.id.btMedicalHistoryToProfile);

		exitToProfile.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				finish();

			}
		});
    }

}
