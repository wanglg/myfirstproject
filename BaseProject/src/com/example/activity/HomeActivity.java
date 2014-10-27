package com.example.activity;

import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.widget.DrawerLayout;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewParent;

import com.example.baseproject.R;
import com.example.fragment.MusicFragment;
import com.example.fragment.PhotoFragment;
import com.example.fragment.VideoFragment;
import com.example.fragment.tablisener.TabFragmentListener;

public class HomeActivity extends BaseActivity implements OnClickListener {
	private ActionBarDrawerToggle mDrawerToggle;
	private ActionBar actionBar;
	DrawerLayout mDrawerLayout;
	private CharSequence mTitle;
	private CharSequence mDrawerTitle;
	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.home_layout);
		initBar();
		initDrawer();
		initTab();
		
	}

	private void initDrawer() {
		mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
		mDrawerToggle = new ActionBarDrawerToggle(this, /* host Activity */
		mDrawerLayout, /* DrawerLayout object */
		R.drawable.ic_drawer, /* nav drawer image to replace 'Up' caret */
		R.string.drawer_open, /* "open drawer" description for accessibility */
		R.string.drawer_close /* "close drawer" description for accessibility */
		) {
			public void onDrawerClosed(View view) {
				actionBar.setTitle(mTitle);
				invalidateOptionsMenu();
				/*getActionBar().setTitle(mTitle);
				invalidateOptionsMenu(); */// creates call to
											// onPrepareOptionsMenu()
			}

			public void onDrawerOpened(View drawerView) {
				
				invalidateOptionsMenu();
				/*getActionBar().setTitle(mDrawerTitle);
				invalidateOptionsMenu(); */// creates call to
											// onPrepareOptionsMenu()
			}
		};
		mDrawerLayout.setDrawerListener(mDrawerToggle);

	}
	private void initBar(){
		actionBar = getActionBar();
		actionBar.setDisplayHomeAsUpEnabled(true);
		actionBar.setHomeButtonEnabled(true);
		mDrawerTitle=mTitle=getTitle();
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);  
	}


	private void initTab() {

		

		
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
	
	@Override
	public void setTitle(CharSequence title) {
		mTitle=title;
		actionBar.setTitle(mTitle);
	}

}
