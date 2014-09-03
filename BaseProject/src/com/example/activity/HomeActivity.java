package com.example.activity;


import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.RelativeLayout;

import com.example.baseproject.R;

public class HomeActivity extends BaseActivity implements OnClickListener {
	RelativeLayout bottom_bar_one,bottom_bar_two,bottom_bar_three;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.home_layout);
		initView();
	}
	private void initView() {
		bottom_bar_one=(RelativeLayout) findViewById(R.id.video_layout);
		bottom_bar_two=(RelativeLayout) findViewById(R.id.music_layout);
		bottom_bar_three=(RelativeLayout) findViewById(R.id.photo_layout);
		bottom_bar_one.setOnClickListener(this);
		bottom_bar_two.setOnClickListener(this);
		bottom_bar_three.setOnClickListener(this);
	}
	@Override
	public void onClick(View arg0) {
		ViewParent vp=arg0.getParent();
		if (vp!=null) {
			((ViewGroup)vp).dispatchSetSelected(false);
			arg0.setSelected(true);
		}
	}

}
