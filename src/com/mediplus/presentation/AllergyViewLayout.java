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

	// String allergies[]={"allergy1","Allergy2","allergy3"};
	ArrayList<String> allergies;
	private Allergy curAllergy;
	ArrayList<Allergy> curAllergyList;

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		// TODO Auto-generated method stub

		super.onListItemClick(l, v, position, id);
		// String str=allergies[position];
		try {

			// Intent intent=new Intent(MenuLayout.this, ourClass);
			// startActivity(intent);
			Toast.makeText(getApplicationContext(), allergies.get(position),
					Toast.LENGTH_LONG).show();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setup();
		/*
		setListAdapter(new ArrayAdapter<String>(AllergyViewLayout.this,
				R.layout.allergylistitem, allergies));
	*/
	}

	private void setup() {

		curAllergy = new Allergy();
		curAllergyList = AllergyManager.getAllergyManager().getAllergyList(
				getApplicationContext(), "Sunimal Rathnayake");
		curAllergy.setAllergy("test Allergy");
		curAllergyList.add(curAllergy);
		Toast.makeText(getApplicationContext(),curAllergy.getAllergy(), Toast.LENGTH_LONG).show();
		//allergies.add(curAllergy.getAllergy());		
		/*
		 * if (curAllergyList.size() > 0) {
		 * allergyProfile.setText(curAllergyList.get(index).getUser());
		 * etAllergy.setText(curAllergyList.get(index).getAllergy());
		 * etSymptoms.setText(curAllergyList.get(index).getSymptoms());
		 * etTreatment.setText(curAllergyList.get(index).getTreatment()); } else
		 * Toast.makeText(getApplicationContext(),
		 * "No Allergies for this user..!!", Toast.LENGTH_LONG).show();
		 */
	}

}
