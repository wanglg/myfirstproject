package com.example.activity;

import android.app.Application;

public class AppManger extends Application {
	public static AppManger instant;

	@Override
	public void onCreate() {
		super.onCreate();
		instant = this;
	}

	public static AppManger getInstant() {
		return instant;
	}

}
