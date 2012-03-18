package com.mediplus.presentation;


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

public class IndividualAllergy extends Activity{
	
	private TextView tvAllergy;
	private TextView tvSymptoms;
	private TextView tvTreatment;
	private Button btEdit;
	private Button btDelete;
	private Allergy curAllergy;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.individualallergy);
		setup();
		eventListeners();
	}
	
	private void setup(){
		
		tvAllergy=(TextView)findViewById(R.id.tvIndividualAllergyAllergy);
		tvSymptoms=(TextView)findViewById(R.id.tvIndividualAllergySymptom);
		tvTreatment=(TextView)findViewById(R.id.tvIndividualAllergyTreatment);
		btEdit=(Button)findViewById(R.id.btIndividualAllergyEdit);
		
		this.curAllergy=AllergyManager.getAllergyManager().getCurrentAllergy();
		
		tvAllergy.setText(curAllergy.getAllergy());
		tvSymptoms.setText(curAllergy.getSymptoms());
		tvTreatment.setText(curAllergy.getTreatment());
		
		}
	
private void eventListeners(){
		
		btEdit.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent=new Intent("com.mediplus.presentation.CURRENT_ALLERGY_EDIT");
				startActivity(intent);
			}
		});
		
	}

}
