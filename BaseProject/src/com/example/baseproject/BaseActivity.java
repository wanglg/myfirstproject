package com.example.baseproject;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class BaseActivity extends Activity {
	public Context mContext;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		mContext=this;
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
 /*   public void showToast(String paramString, int paramInt) {
		LayoutInflater localLayoutInflater = LayoutInflater.from(this);
		// ViewGroup localViewGroup = (ViewGroup)findViewById(2131427910);
		View localView = localLayoutInflater.inflate(R.layout.toast_layout,
				null);
		((TextView) localView.findViewById(R.id.toastText))
				.setText(paramString);
		Toast localToast = new Toast(this);
		localToast.setDuration(paramInt);
		localToast.setView(localView);
		localToast.setGravity(17, 0, 220);
		localToast.show();
	}
*/
}
