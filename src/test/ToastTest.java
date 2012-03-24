package test;



import com.mediplus.core.CurrentUser;

import android.app.Activity;
import android.widget.Toast;

public class ToastTest{
	
	private static ToastTest instance;
	
	private ToastTest() {

	}

	public static ToastTest getToastTest() {

		if (instance == null)
			instance = new ToastTest();

		return instance;

	}
	
	public void toastTest(String str){
		
		Toast.makeText(CurrentUser.getCurrentUser().getCtx(), str, Toast.LENGTH_SHORT).show();
		
	}

}
