package com.mediplus.presentation;

import java.util.ArrayList;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import com.mediplus.core.UserProfileManager;
import com.mediplus.entity.Allergy;

public class ExistingProfileMenu  extends ListActivity{
	ArrayList<String> profiles;
	ArrayList<Integer> profilePosition;
	private Allergy curAllergy;
	ArrayList<String> curProfileList;

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {

		super.onListItemClick(l, v, position, id);
		try {
			curAllergy = new Allergy();
			UserProfileManager.getUserProfileManager().setCurrentSecondaryProfile(curProfileList.get(profilePosition.get(position)));
			Intent intent = new Intent(	"com.mediplus.presentation.SECONDARYPROFILELAYOUT");
			startActivity(intent);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setup();

		setListAdapter(new ArrayAdapter<String>(ExistingProfileMenu.this,
				R.layout.allergylistitem, profiles));

	}

	private void setup() {
		profiles = new ArrayList<String>();
		profilePosition = new ArrayList<Integer>();
		curAllergy = new Allergy();
		curProfileList=UserProfileManager.getUserProfileManager().getSecondaryProfileList(getApplicationContext());
		
		int idx = curProfileList.size() - 1;
		while (idx >= 0) {
			profiles.add(curProfileList.get(idx));
			profilePosition.add(idx);
			idx--;
		}
		
	}
	
}
