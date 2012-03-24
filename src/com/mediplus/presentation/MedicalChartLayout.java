package com.mediplus.presentation;

import java.util.ArrayList;

import com.mediplus.core.CurrentUser;
import com.mediplus.core.MedicalChartManager;
import com.mediplus.entity.ElementData;
import com.mediplus.entity.MedicalChartRecord;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

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
		
		TableRow tbrow = new TableRow(getApplicationContext());
		tbrow.setOrientation(TableRow.HORIZONTAL);

		TextView temp1 = new TextView(getApplicationContext());
		temp1.setText("Label");
		TextView temp2 = new TextView(getApplicationContext());
		temp2.setText("Value");

		tbrow.addView(temp1);
		tbrow.addView(temp2);
		table.addView(tbrow);
		for (ElementData e : elementData) {
			TextView tv1 = new TextView(getApplicationContext());
			tv1.setText(e.getDateTime());
			TextView tv2 = new TextView(getApplicationContext());
			tv2.setText(e.getValue().toString());
			TableRow row = new TableRow(getApplicationContext());
			row.addView(tv1);
			row.addView(tv2);
			table.addView(row);
			
		}
		

	}

}
