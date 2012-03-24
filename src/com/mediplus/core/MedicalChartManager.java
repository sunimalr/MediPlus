package com.mediplus.core;

import java.util.ArrayList;

import test.ToastTest;

import android.content.Context;
import android.database.Cursor;
import android.widget.Toast;

import com.mediplus.entity.Allergy;
import com.mediplus.entity.ElementData;
import com.mediplus.entity.MedicalChartRecord;
import com.mediplus.persistence.DatabaseUtil;

public class MedicalChartManager {

	private static MedicalChartManager instance = null;;
	DatabaseUtil dbUtil;
	private String curChart;
	ArrayList<ElementData> plotData;

	public ArrayList<ElementData> getPlotData() {
		return plotData;
	}

	public void setPlotData(ArrayList<ElementData> plotData) {
		this.plotData = plotData;
	}

	private MedicalChartManager() {

	}

	public static MedicalChartManager getMedicalChartManager() {

		if (instance == null)
			instance = new MedicalChartManager();

		return instance;

	}

	public void addNewMedicalChartRecord(MedicalChartRecord record, Context ctx) {

		dbUtil = new DatabaseUtil(ctx);
		dbUtil.open();
		dbUtil.addMedicalChartRecord(record);
		dbUtil.close();

	}
	
	public String getCurrentChart(){
		return this.curChart;
	}
	
	public void setCurrentChart(String a){
		this.curChart=a;
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
		MedicalChartRecord r=new MedicalChartRecord();
		MedicalChartRecord r1=new MedicalChartRecord();
		r.setProfile(CurrentUser.getCurrentUser().getCurrentUserName());
		r.setMedicalChart("Test Chart");
		r.setDateTime("24/03/2012 12:12");
		r.setValue(Float.parseFloat("11.24"));
		
		r1.setProfile(CurrentUser.getCurrentUser().getCurrentUserName());
		r1.setMedicalChart("Test Chart 2");
		r1.setDateTime("24/03/2012 1:47");
		r1.setValue(Float.parseFloat("11.20"));
		
		dbUtil.addMedicalChartRecord(r);
		dbUtil.addMedicalChartRecord(r1);
		dbUtil.close();
		dbUtil.open();
		ArrayList<String> templist = new ArrayList<String>();
		Cursor cursor = dbUtil.fetchMedicalChartsList(profile);
		ToastTest.getToastTest().toastTest("data 1= "+cursor.getString(1));
		if (cursor != null) {
			String temp = new String();
			temp = cursor.getString(1);
			templist.add(temp);
			while (cursor.moveToNext()) {
				//String temp = new String();
				temp = cursor.getString(1);
				templist.add(temp);
				ToastTest.getToastTest().toastTest("data = "+cursor.getString(1));

			}

		} else{
			Toast.makeText(ctx, "Cannot load medical charts list..",
					Toast.LENGTH_LONG);
		}
		dbUtil.close();

		return templist;

	}

}
