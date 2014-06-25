package com.example.baseproject;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.widget.Toast;

public class BaseActivity extends Activity {
	public Context mContext;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		mContext=this;
	}
	public void messageTip(String tip){
		messageTip(tip, Toast.LENGTH_LONG);
	}
    public void messageTip(int resID){
    	
    	messageTip(mContext.getResources().getString(resID), Toast.LENGTH_LONG);
    	
	}
    public void messageTip(String tip,int length_mode ){
    	Toast.makeText(mContext, tip, Toast.LENGTH_LONG).show();
    	
	}

}
