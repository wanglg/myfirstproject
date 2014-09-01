package com.example.bean;

import android.app.Activity;
import android.content.Context;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.view.Display;

import com.example.util.LogUtil;
/**
 * 设备对象
 * @author xingchen
 *
 */
public class Device {
	private final String TAG="wang";
	private static Device device=new Device();
	private Device(){
		
	}
	public static Device getInstance(){
		return device;
		
	}
	public void init(Activity context){
		
		Display display =context.getWindowManager().getDefaultDisplay();
		int width = display.getWidth();
		int height = display.getHeight();
		
		WifiManager wifi = (WifiManager)context. getSystemService (Context.WIFI_SERVICE);
        WifiInfo info = wifi.getConnectionInfo();
        setMAC_address(info.getMacAddress()) ;
        setSystemVersion(android.os.Build.VERSION.SDK_INT);
		setWidth(width);
		setHeight(height);
		LogUtil.i(TAG, "width-->"+width+"  height---> "+height+"" +
				"MAC_address-->"+MAC_address+"  SystemVersion--->"+getSystemVersion());
	}
	int width;
	int height;
	String MAC_address;
	int systemVersion;
	public int getWidth() {
		return width;
	}
	public void setWidth(int width) {
		this.width = width;
	}
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}
	public String getMAC_address() {
		return MAC_address;
	}
	public void setMAC_address(String mAC_address) {
		MAC_address = mAC_address;
	}
	public int getSystemVersion() {
		return systemVersion;
	}
	public void setSystemVersion(int systemVersion) {
		this.systemVersion = systemVersion;
	}

	

}