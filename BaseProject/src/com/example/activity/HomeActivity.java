package com.example.activity;

import java.util.ArrayList;
import java.util.List;

import android.app.ActionBar;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.DrawerLayout.DrawerListener;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.baseproject.R;
import com.example.fragment.MusicFragment;
import com.example.fragment.PhotoFragment;
import com.example.fragment.VideoFragment;

public class HomeActivity extends BaseActivity implements OnClickListener, OnItemClickListener {
	// private ActionBar actionBar;
	DrawerLayout mDrawerLayout;
	private ActionBarDrawerToggle mDrawerToggle;
	private ActionBarHelper mActionBar;

	
	private ListView left_drawer;
	ViewPager mViewPager;
	private List<Fragment> fragmentList;

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.home_layout);
		fragmentList = new ArrayList<Fragment>();
		fragmentList.add(PhotoFragment.getInstance());
		fragmentList.add(VideoFragment.getInstance());

		fragmentList.add(MusicFragment.getInstance());
		//initBar();
		initDrawer();
		initView();

	}

	private void initView() {
		mViewPager = (ViewPager) findViewById(R.id.home_viewpager);
		mViewPager.setAdapter(new MyViewPagerAdapter(
				getSupportFragmentManager()));
		left_drawer=(ListView) findViewById(R.id.left_drawer);
		left_drawer.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,
                TITLES));
		//left_drawer.setSelector(R.drawable.item_selector);
		left_drawer.setOnItemClickListener(this);
	}

	private void initDrawer() {
		mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
		mDrawerLayout.setDrawerTitle(GravityCompat.START, getString(R.string.drawer_open));
		mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout,
                R.string.drawer_open, R.string.drawer_close);
		
		mActionBar=new ActionBarHelper();
		mActionBar.init();

		mDrawerLayout.setDrawerListener(new DrawerListener() {

			@Override
			public void onDrawerStateChanged(int newState) {
				mDrawerToggle.onDrawerStateChanged(newState);

			}

			@Override
			public void onDrawerSlide(View drawerView, float progress) {
				mDrawerToggle.onDrawerSlide(drawerView, progress);

			}

			@Override
			public void onDrawerOpened(View drawerView) {
				mDrawerToggle.onDrawerOpened(drawerView);
				mActionBar.onDrawerOpened();
			}

			@Override
			public void onDrawerClosed(View drawerView) {
				mDrawerToggle.onDrawerClosed(drawerView);
				mActionBar.onDrawerClosed();
			}
		});

	}

	/*private void initBar() {
		// actionBar = getActionBar();
		// actionBar.setDisplayHomeAsUpEnabled(true);
		// actionBar.setHomeButtonEnabled(true);
		mDrawerTitle = mTitle = getTitle();
		// actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
	}*/

	private class MyViewPagerAdapter extends FragmentPagerAdapter {

		public MyViewPagerAdapter(FragmentManager fm) {
			super(fm);

		}

		@Override
		public Fragment getItem(int arg0) {
			return fragmentList.get(arg0);
		}

		@Override
		public int getCount() {
			return fragmentList.size();
		}

	}

	/**
	 * Action bar helper for use on ICS and newer devices.
	 */
	private class ActionBarHelper {
		private final ActionBar mActionBar;
		private CharSequence mDrawerTitle;
		private CharSequence mTitle;

		ActionBarHelper() {
			mActionBar = getActionBar();
		}

		public void init() {
			mActionBar.setDisplayHomeAsUpEnabled(true);
			mActionBar.setDisplayShowHomeEnabled(false);
			mTitle = mDrawerTitle = getTitle();
		}

		/**
		 * When the drawer is closed we restore the action bar state reflecting
		 * the specific contents in view.
		 */
		public void onDrawerClosed() {
			mActionBar.setTitle(mTitle);
		}

		/**
		 * When the drawer is open we set the action bar to a generic title. The
		 * action bar should only contain data relevant at the top level of the
		 * nav hierarchy represented by the drawer, as the rest of your content
		 * will be dimmed down and non-interactive.
		 */
		public void onDrawerOpened() {
			mActionBar.setTitle(mDrawerTitle);
		}

		public void setTitle(CharSequence title) {
			mTitle = title;
		}
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

		//MenuItem searchItem = menu.findItem(R.id.action_search);

		//searchItem.getActionView();
		return super.onCreateOptionsMenu(menu);
	}

	public static final String[] TITLES = { "Henry IV (1)", "Henry V",
			"Henry VIII", "Richard II", "Richard III", "Merchant of Venice",
			"Othello", "King Lear" };

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,long id) {
		switch (parent.getId()) {
		case R.id.left_drawer:
			 mActionBar.setTitle(TITLES[position]);
	         mDrawerLayout.closeDrawer(parent);
			break;

		default:
			break;
		}
		
	}

}
