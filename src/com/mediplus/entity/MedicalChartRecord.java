package com.mediplus.entity;

public class MedicalChartRecord {

	private String profile;
	private String medicalChart;
	private String dateTime;
	private float value;

	
	public String getProfile() {
		return profile;
	}
	public void setProfile(String profile) {
		this.profile = profile;
	}
	public String getMedicalChart() {
		return medicalChart;
	}
	public void setMedicalChart(String medicalChart) {
		this.medicalChart = medicalChart;
	}
	public String getDateTime() {
		return dateTime;
	}
	public void setDateTime(String dateTime) {
		this.dateTime = dateTime;
	}
	public float getValue() {
		return value;
	}
	public void setValue(float value) {
		this.value = value;
	}

}
