package com.mediplus.core;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.widget.Toast;

import com.mediplus.entity.Allergy;
import com.mediplus.entity.User;
import com.mediplus.persistence.DatabaseUtil;

/*
 * Responsible for handling logic and connection between the databse layer and GUI front end allergy layout.
 */
public class AllergyManager extends Activity{

	DatabaseUtil dbUtil;

	Allergy curAllergy;

	private static AllergyManager instance = null;;

	private AllergyManager() {

	}

	public static AllergyManager getAllergyManager() {

		if (instance == null)
			instance = new AllergyManager();

		return instance;

	}

	public void addNewAllergey(Allergy allergy, Context ctx) {

		dbUtil = new DatabaseUtil(ctx);
		dbUtil.open();
		dbUtil.addAllergy(allergy);
		dbUtil.close();

	}
	
	public void goToAllergy(Context ctx, Allergy a){
		this.curAllergy=a;
		
		Intent intent=new Intent("com.mediplus.presentation.INDIVIDUALALLERGY");
		startActivity(intent);
		
		
	}
	
	public Allergy getCurrentAllergy(){
		return this.curAllergy;
	}
	
	public void setCurrentAllergy(Allergy a){
		this.curAllergy=a;
	}


	/*
	 * returns a arraylist with fetched records
	 */
	public ArrayList<Allergy> getAllergyList(Context ctx, String profile) {

		dbUtil = new DatabaseUtil(ctx);
		dbUtil.open();
		ArrayList<Allergy> templist = new ArrayList<Allergy>();

		//Stack<Allergy> stk=new Stack<Allergy>();
		

		Cursor cursor = dbUtil.fetchProfileAllergy(profile);

		if (cursor != null) {
			int idx=0;
			while (cursor.moveToNext()) {
				Allergy temp = new Allergy();
				temp.setUser(profile);
				temp.setAllergy(cursor.getString(1));
				temp.setSymptoms(cursor.getString(2));
				temp.setTreatment(cursor.getString(3));
				templist.add(temp);
				//stk.push(temp);
				//Toast.makeText(ctx, "al2 = "+ temp.getAllergy() + " size= "+templist.size()+" ele0 = "+templist.get(0).getAllergy() ,
					//	 Toast.LENGTH_SHORT).show();
								
				idx++;

			}

		} else
			Toast.makeText(ctx, "Cannot load allergies", Toast.LENGTH_LONG);

		dbUtil.close();
		
		return templist;

	}

}
