package com.mediplus.presentation;

import java.util.ArrayList;

import com.mediplus.core.CurrentUser;
import com.mediplus.core.MedicalChartManager;
import com.mediplus.entity.ElementData;
import com.mediplus.entity.MedicalChartRecord;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

public class MedicalChartLayout extends Activity {

	

	TableLayout table;
	ArrayList<TableRow> elements;
	ArrayList<ElementData> elementData;
	ArrayList<MedicalChartRecord> tempRecList;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setup();
		setContentView(this.table);

	}

	private void setup() {
		table = new TableLayout(getApplicationContext());
		table.setStretchAllColumns(true);

		elements = new ArrayList<TableRow>();
		elementData = new ArrayList<ElementData>();

		
		tempRecList=MedicalChartManager.getMedicalChartManager().getMedicalChartRecords(
				getApplicationContext(),
				CurrentUser.getCurrentUser().getCurrentUserName(),
				MedicalChartManager.getMedicalChartManager().getCurrentChart());

		elementData=new ArrayList<ElementData>();
		for (MedicalChartRecord m : tempRecList) {
			ElementData d=new ElementData();
			d.setDateTime(m.getDateTime());
			d.setValue(m.getValue());
			elementData.add(d);				
		}
		MedicalChartManager.getMedicalChartManager().setPlotData(elementData);
		TableRow tbrow = new TableRow(getApplicationContext());
		tbrow.setOrientation(TableRow.HORIZONTAL);

		TextView temp1 = new TextView(getApplicationContext());
		temp1.setText("Date & Time");
		TextView temp2 = new TextView(getApplicationContext());
		temp2.setText("Value");

		tbrow.addView(temp1);
		tbrow.addView(temp2);
		table.addView(tbrow);
		int index=0;
		for (ElementData e : elementData) {
			index++;
			TextView tv1 = new TextView(getApplicationContext());
			tv1.setText(e.getDateTime());
			TextView tv2 = new TextView(getApplicationContext());
			tv2.setText(e.getValue().toString());
			tv1.setClickable(true);
			tv1.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					
					Toast.makeText(getApplicationContext(), "test click "+((TextView)v).getText().toString(), Toast.LENGTH_SHORT).show();
					MedicalChartManager.getMedicalChartManager().setClickedRecord(((TextView)v).getText().toString());
					Intent in=new Intent("com.mediplus.presentation.EDITCHARTRECORDLAYOUT");
					startActivity(in);
					finish();
				}
			});
			TableRow row = new TableRow(getApplicationContext());
			row.addView(tv1);
			row.addView(tv2);
			table.addView(row);
			
			
		}
		Button b=new Button(getApplicationContext());
		b.setText("View Graph");
		b.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				try{
					Class ourClass=Class.forName("com.mediplus.presentation.Graph");
				
					Intent intent=new Intent(MedicalChartLayout.this, ourClass);
					startActivity(intent);
					}catch(ClassNotFoundException e){
						e.printStackTrace();
					}
				Toast.makeText(getApplicationContext(), "buttontest", Toast.LENGTH_SHORT).show();
				
			}
		});
		
		table.addView(b);
		

	}

}
