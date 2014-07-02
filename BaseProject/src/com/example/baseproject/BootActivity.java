package com.example.baseproject;

import com.example.bean.Device;

import android.os.Bundle;
import android.preference.PreferenceManager;
import android.app.Activity;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.view.Menu;

public class BootActivity extends BaseActivity {
	public final String PACKAGE_NAME="com.example.baseproject";
	public final String VERSION_KEY="version";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(android.R.style.Theme_Translucent_NoTitleBar);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_boot);
        showToast("!!!!!ewhjoaihsdohh");
    }

    public boolean isFirstInstall(){
    	PackageInfo info=null;
		try {
			info = getPackageManager().getPackageInfo(PACKAGE_NAME, 0);
		} catch (NameNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	int currentVersion = info.versionCode;
    	SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
    	int lastVersion = prefs.getInt(VERSION_KEY, 0);
    	if (currentVersion > lastVersion) {
    	     //���ǰ�汾�����ϴΰ汾���ð汾���ڵ�һ������
    	  
    	     //����ǰ�汾д��preference�У����´�������ʱ�򣬾ݴ��жϣ�����Ϊ�״�����
    	     prefs.edit().putInt(VERSION_KEY,currentVersion).commit();
    	     return true;
    	}else{
    		 return false;
    	}
    }
    
    public void initDevice(){
    	Device device=Device.getInstance();
    	device.init(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.boot, menu);
        return true;
    }
    
}
