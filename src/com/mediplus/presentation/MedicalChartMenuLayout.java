package com.mediplus.presentation;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MedicalChartMenuLayout extends Activity{

	Button btCreateNewChart, btOpenExistingChart;
	
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.medicalchartmenu);
		this.setEventListeners();

	}
	
	private void setEventListeners(){
		
		btCreateNewChart=(Button)findViewById(R.id.btCreateNewChart);
		btOpenExistingChart=(Button)findViewById(R.id.btOpenExistingChart);
		
		btCreateNewChart.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
			}
		});
		
		btOpenExistingChart.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
			}
		});
		
	}

	
	
	
}
