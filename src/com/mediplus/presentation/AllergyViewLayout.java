package com.mediplus.presentation;

import java.util.ArrayList;

import com.mediplus.core.AllergyManager;
import com.mediplus.entity.Allergy;
import com.mediplus.entity.User;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class AllergyViewLayout extends ListActivity {

	ArrayList<String> allergies;
	ArrayList<Integer> allergyPosition;
	private Allergy curAllergy;
	ArrayList<Allergy> curAllergyList;

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {

		super.onListItemClick(l, v, position, id);
		try {
			curAllergy = new Allergy();
			AllergyManager.getAllergyManager().setCurrentAllergy(
					curAllergyList.get(allergyPosition.get(position)));
			Intent intent = new Intent(
					"com.mediplus.presentation.INDIVIDUALALLERGY");
			startActivity(intent);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setup();

		setListAdapter(new ArrayAdapter<String>(AllergyViewLayout.this,
				R.layout.allergylistitem, allergies));

	}

	private void setup() {
		allergies = new ArrayList<String>();
		allergyPosition = new ArrayList<Integer>();
		curAllergy = new Allergy();
		curAllergyList = AllergyManager.getAllergyManager().getAllergyList(
				getApplicationContext(), "Sunimal Rathnayake");

		int idx = curAllergyList.size() - 1;
		for (Allergy c : curAllergyList) {

		}
		while (idx >= 0) {
			allergies.add(curAllergyList.get(idx).getAllergy());
			allergyPosition.add(idx);
			idx--;
		}

	}
	
	

}
