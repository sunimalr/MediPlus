package com.mediplus.presentation;




import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class SplashScreenLayout extends Activity{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.splashscreen);
		Thread timer=new Thread(){
			public void run(){
				try{
					sleep(4000);
				}catch(InterruptedException e){
					e.printStackTrace();
				}finally{
					Intent openMenu=new Intent("com.mediplus.presentation.MENU");
					startActivity(openMenu);
				}
				
			}
		};
		timer.start();
		
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		finish();
	}
	
	
	
	
}
