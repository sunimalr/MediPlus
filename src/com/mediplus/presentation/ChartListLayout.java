package com.mediplus.presentation;

import java.util.ArrayList;

import com.mediplus.core.AllergyManager;
import com.mediplus.core.CurrentUser;
import com.mediplus.core.MedicalChartManager;
import com.mediplus.entity.Allergy;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class ChartListLayout extends ListActivity {

	ArrayList<String> charts;
	ArrayList<Integer> chartPosition;
	ArrayList<String> curChartList;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setup();
		setListAdapter(new ArrayAdapter<String>(ChartListLayout.this,
				R.layout.allergylistitem, charts));
	}

	private void setup() {
		charts = new ArrayList<String>();
		chartPosition = new ArrayList<Integer>();
		curChartList = MedicalChartManager.getMedicalChartManager()
				.getMedicalChartList(getApplicationContext(),
						CurrentUser.getCurrentUser().getCurrentUserName());
		

		int idx = curChartList.size() - 1;
		for (String c : curChartList) {

		}
		while (idx >= 0) {
			charts.add(curChartList.get(idx));
			chartPosition.add(idx);
			idx--;
		}
		charts.add("size "+ curChartList.size());

	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
	
		super.onListItemClick(l, v, position, id);
		try {
			//curAllergy = new Allergy();
			MedicalChartManager.getMedicalChartManager().setCurrentChart(curChartList.get(chartPosition.get(position)));
			try{
				Class ourClass=Class.forName("com.mediplus.presentation.MedicalChartLayout");
			
				Intent intent=new Intent(ChartListLayout.this, ourClass);
				startActivity(intent);
				}catch(ClassNotFoundException e){
					e.printStackTrace();
				}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
