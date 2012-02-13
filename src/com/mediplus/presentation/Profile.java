package com.mediplus.presentation;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class Profile extends Activity{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.profilemain);
		eventListeners();
	}
	
	Button profileToMenu;
	
	private void eventListeners(){
		
		profileToMenu=(Button)findViewById(R.id.btProfileToMenu);
		
		profileToMenu.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent openMenu=new Intent("com.mediplus.presentation.MENU");
				startActivity(openMenu);
				
			}
		});
		
	}

}
