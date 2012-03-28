package com.mediplus.presentation;

import com.mediplus.core.CurrentUser;
import com.mediplus.core.MedicalChartManager;
import com.mediplus.entity.MedicalChartRecord;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class EditChartRecordLayout extends Activity {

	private Button btSave;
	private TextView tvChartName;
	private EditText etDate, etTime, etValue;
	private MedicalChartRecord oldMedirec;

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.editchartrecordlayout);
		setup();
		setEventListeners();
	}

	private void setup() {
		oldMedirec=new MedicalChartRecord();
		btSave=(Button)findViewById(R.id.btSaveIndMedicalRecord);
		tvChartName=(TextView)findViewById(R.id.tvMediRecEditChart);
		etDate=(EditText)findViewById(R.id.etChartRecDate);
		etTime=(EditText)findViewById(R.id.etChartRecTime);
		etValue=(EditText)findViewById(R.id.etChartRecVal);

		String datetime=MedicalChartManager.getMedicalChartManager().getClickedRecord();
		String[] temp;
		temp=datetime.split(" ");
		
		tvChartName.setText(tvChartName.getText()+MedicalChartManager.getMedicalChartManager().getCurrentChart());
		etDate.setText(temp[0]);
		etTime.setText(temp[1]);
		
		oldMedirec.setProfile(CurrentUser.getCurrentUser().getCurrentUserName());
		oldMedirec.setMedicalChart(MedicalChartManager.getMedicalChartManager().getCurrentChart());
		oldMedirec.setDateTime(datetime);
		
		
		
		MedicalChartRecord mr=MedicalChartManager.getMedicalChartManager()
				.getMedicalChartRecord(
						MedicalChartManager.getMedicalChartManager()
								.getCurrentChart(),
						MedicalChartManager.getMedicalChartManager()
								.getClickedRecord(), getApplicationContext());

		etValue.setText(String.valueOf(mr.getValue()));
		oldMedirec.setValue(mr.getValue());
	}
	
	private void setEventListeners(){
		
		btSave.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				MedicalChartRecord m=new MedicalChartRecord();
				m.setProfile(CurrentUser.getCurrentUser().getCurrentUserName());
				m.setMedicalChart(MedicalChartManager.getMedicalChartManager().getCurrentChart());
				m.setDateTime(etDate.getText().toString()+" "+etTime.getText().toString());
				m.setValue(Float.parseFloat(etValue.getText().toString()));
				MedicalChartManager.getMedicalChartManager().updateMedicalChartRecord(m,oldMedirec, getApplicationContext());
				finish();
				
			}
		});
		
	}

}
