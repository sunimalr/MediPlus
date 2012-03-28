package com.mediplus.core;

import java.util.ArrayList;

import test.ToastTest;

import android.content.Context;
import android.database.Cursor;
import android.widget.Toast;

import com.mediplus.entity.Allergy;
import com.mediplus.entity.ElementData;
import com.mediplus.entity.MedicalChartRecord;
import com.mediplus.entity.MedicalRecord;
import com.mediplus.persistence.DatabaseUtil;

public class MedicalChartManager {

	private static MedicalChartManager instance = null;;
	DatabaseUtil dbUtil;
	private String curChart;
	ArrayList<ElementData> plotData;
	private String clickedRecord;
	private ArrayList<ElementData> chartData;

	public String getClickedRecord() {
		return clickedRecord;
	}

	public MedicalChartRecord getMedicalChartRecord(String chart,
			String datetime, Context ctx) {

		dbUtil = new DatabaseUtil(ctx);
		dbUtil.open();
		Cursor cursor = dbUtil.fetchIndividualMedicalChartRecords(CurrentUser
				.getCurrentUser().getCurrentUserName(), chart, datetime);

		MedicalChartRecord temp = new MedicalChartRecord();
		try {

			if (cursor != null) {
				temp.setProfile(cursor.getString(0));
				temp.setMedicalChart(cursor.getString(1));
				temp.setDateTime(cursor.getString(2));
				temp.setValue(cursor.getFloat(3));

			}
		} catch (Exception e) {

			Toast.makeText(ctx, "Cannot load medical chart " + chart
					+ " records", Toast.LENGTH_LONG);
		}
		dbUtil.close();
		return temp;

	}

	public void setClickedRecord(String clickedRecord) {
		this.clickedRecord = clickedRecord;
	}

	public ArrayList<ElementData> getPlotData() {
		return chartData;
	}

	public void setPlotData(ArrayList<ElementData> plotData) {
		this.chartData=plotData;
	}

	private MedicalChartManager() {

	}

	public static MedicalChartManager getMedicalChartManager() {

		if (instance == null)
			instance = new MedicalChartManager();

		return instance;

	}

	public void updateMedicalChartRecord(MedicalChartRecord record,
			MedicalChartRecord oldrec, Context ctx) {

		dbUtil = new DatabaseUtil(ctx);
		dbUtil.open();
		dbUtil.updateMedicalChartRecord(record, oldrec);
		dbUtil.close();

	}

	public String getCurrentChart() {
		return this.curChart;
	}

	public void setCurrentChart(String a) {
		this.curChart = a;
	}

	

	public void addMedicalChartRecord(MedicalChartRecord m, Context ctx) {
		dbUtil = new DatabaseUtil(ctx);
		dbUtil.open();
		dbUtil.addMedicalChartRecord(m);
		dbUtil.close();

	}

	public ArrayList<MedicalChartRecord> getMedicalChartRecords(Context ctx,
			String profile, String chart) {

		dbUtil = new DatabaseUtil(ctx);
		dbUtil.open();
		ArrayList<MedicalChartRecord> templist = new ArrayList<MedicalChartRecord>();
		Cursor cursor = dbUtil.fetchMedicalChartsRecords(profile, chart);

		if (cursor != null) {
			int idx = 0;
			while (cursor.moveToNext()) {
				MedicalChartRecord temp = new MedicalChartRecord();
				temp.setProfile(cursor.getString(0));
				temp.setMedicalChart(cursor.getString(1));
				temp.setDateTime(cursor.getString(2));
				temp.setValue(cursor.getFloat(3));
				templist.add(temp);

				idx++;

			}

		} else
			Toast.makeText(ctx, "Cannot load medical chart " + chart
					+ " records", Toast.LENGTH_LONG);

		dbUtil.close();

		return templist;

	}

	public ArrayList<String> getMedicalChartList(Context ctx, String profile) {

		dbUtil = new DatabaseUtil(ctx);
		dbUtil.open();
		dbUtil.close();
		dbUtil.open();
		
		ArrayList<String> templist = new ArrayList<String>();
		try{
		Cursor cursor = dbUtil.fetchMedicalChartsList(profile);
		
		//ToastTest.getToastTest().toastTest("data 1= " + cursor.getString(1));
		if (cursor != null) {
			String temp = new String();
			temp = cursor.getString(1);
			templist.add(temp);
			do {
				// String temp = new String();
				temp = cursor.getString(1);
				templist.add(temp);
				ToastTest.getToastTest().toastTest(
						"data = " + cursor.getString(1));

			} while (cursor.moveToNext());

		} 
		}
		catch(Exception e){
		Toast.makeText(ctx, "Cannot load medical charts list..Make sure you have added medical charts..!",
					Toast.LENGTH_LONG).show();
		}
		dbUtil.close();

		return templist;

	
	}
}
