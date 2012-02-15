package com.mediplus.core;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.database.Cursor;
import android.widget.Toast;

import com.mediplus.entity.Allergy;
import com.mediplus.entity.User;
import com.mediplus.persistence.DatabaseUtil;

public class AllergyManager {
	
	DatabaseUtil dbUtil;
	
	Allergy curAllergy;
	
	private static AllergyManager instance=null;;
	
	private AllergyManager(){
				
	}
	
	public static AllergyManager getAllergyManager(){
		
		if(instance==null)
			instance=new AllergyManager();
		
		return instance;
		
	}
	
	public void addNewAllergey(Allergy allergy,Context ctx) {
		
		dbUtil =new DatabaseUtil(ctx);
		dbUtil.open();
		dbUtil.addAllergy(allergy);
		dbUtil.close();
		
		
	}
	
	public ArrayList<Allergy> getAllergyList(Context ctx,String profile){
		
		dbUtil = new DatabaseUtil(ctx);
		dbUtil.open();
		ArrayList<Allergy> templist=new ArrayList<Allergy>();
		
		Allergy temp=new Allergy();
		
		Cursor cursor = dbUtil.fetchProfileAllergy(profile);

	
		if (cursor != null) {
			while (cursor.moveToNext()) {
				
				temp.setUser(profile);
				temp.setAllergy(cursor.getString(1));
				temp.setSymptoms(cursor.getString(2));
				temp.setTreatment(cursor.getString(3));
				
				templist.add(temp);
				
			}
		} else
			Toast.makeText(ctx, "Cannot load allergies", Toast.LENGTH_LONG);

		dbUtil.close();

		return templist;
		
	}

}
