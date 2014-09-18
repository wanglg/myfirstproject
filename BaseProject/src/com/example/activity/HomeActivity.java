package com.example.activity;

import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.RelativeLayout;

import com.example.baseproject.R;
import com.example.fragment.MusicFragment;
import com.example.fragment.PhotoFragment;
import com.example.fragment.VideoFragment;
import com.example.fragment.tablisener.TabFragmentListener;

public class HomeActivity extends BaseActivity implements OnClickListener {
	RelativeLayout bottom_bar_one, bottom_bar_two, bottom_bar_three;

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		//setContentView(R.layout.home_layout);
		initTab();
		//initView();
	}

	private void initView() {
		bottom_bar_one = (RelativeLayout) findViewById(R.id.video_layout);
		bottom_bar_two = (RelativeLayout) findViewById(R.id.music_layout);
		bottom_bar_three = (RelativeLayout) findViewById(R.id.photo_layout);
		bottom_bar_one.setOnClickListener(this);
		bottom_bar_two.setOnClickListener(this);
		bottom_bar_three.setOnClickListener(this);
	}

	private void initTab() {

		ActionBar actionBar = getActionBar();
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);  
		Tab tab1=actionBar.newTab().setText("Õº∆¨").setTabListener(new 
				TabFragmentListener<PhotoFragment>(HomeActivity.this, "Õº∆¨", PhotoFragment.class));
		actionBar.addTab(tab1);
		Tab tab2=actionBar.newTab().setText("“Ù¿÷").setTabListener(new 
				TabFragmentListener<MusicFragment>(HomeActivity.this, "“Ù¿÷", MusicFragment.class));
		actionBar.addTab(tab2);
		Tab tab3=actionBar.newTab().setText(" ”∆µ").setTabListener(new 
				TabFragmentListener<VideoFragment>(HomeActivity.this, " ”∆µ", VideoFragment.class));
		actionBar.addTab(tab3);
	}

	@Override
	public void onClick(View arg0) {
		ViewParent vp = arg0.getParent();
		if (vp != null) {
			((ViewGroup) vp).dispatchSetSelected(false);
			arg0.setSelected(true);
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.main, menu);
		MenuItem searchItem = menu.findItem(R.id.action_search);
		searchItem.getActionView();
		return super.onCreateOptionsMenu(menu);
	}

}
