package com.example.activity;

import java.lang.reflect.Field;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.TextView;
import android.widget.Toast;

import com.example.baseproject.R;

public class BaseActivity extends FragmentActivity {
	public Context mContext;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		mContext=this;
		//setOverflowShowingAlways();
	}
	private void setOverflowShowingAlways() {  
	    try {  
	        ViewConfiguration config = ViewConfiguration.get(this);  
	        Field menuKeyField = ViewConfiguration.class.getDeclaredField("sHasPermanentMenuKey");  
	        menuKeyField.setAccessible(true);  
	        menuKeyField.setBoolean(config, false);  
	    } catch (Exception e) {  
	        e.printStackTrace();  
	    }  
	}
	public void showToast(String tip){
		showToast(tip, Toast.LENGTH_LONG);
	}
    public void showToast(int resID){
    	
    	showToast(mContext.getResources().getString(resID), Toast.LENGTH_LONG);
    	
	}
    public void showToast(String tip,int length_mode ){
    	LayoutInflater localLayoutInflater = LayoutInflater.from(this);
		// ViewGroup localViewGroup = (ViewGroup)findViewById(2131427910);
		View localView = localLayoutInflater.inflate(R.layout.toast_layout,
				null);
		((TextView) localView.findViewById(R.id.toastText))
				.setText(tip);
		Toast localToast = new Toast(this);
		localToast.setDuration(length_mode);
		localToast.setView(localView);
		localToast.setGravity(17, 0, 220);
		localToast.show();
    	
	}
    
}
