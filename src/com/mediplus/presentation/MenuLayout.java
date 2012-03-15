package com.mediplus.presentation;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MenuLayout extends ListActivity{
	
	String lists[]={"My Profile","Other Profiles","General Reminders","Backup","Sync With Server","Exit"};
	String classes[]={"ProfileLayout","Graph","","","",""};
	
	
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		// TODO Auto-generated method stub
		
		super.onListItemClick(l, v, position, id);
		String str=classes[position];
		try{
		Class ourClass=Class.forName("com.mediplus.presentation."+str);
	
		Intent intent=new Intent(MenuLayout.this, ourClass);
		startActivity(intent);
		}catch(ClassNotFoundException e){
			e.printStackTrace();
		}
		
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setListAdapter(new ArrayAdapter<String>(MenuLayout.this, android.R.layout.simple_list_item_1, lists));
	}

	
}
