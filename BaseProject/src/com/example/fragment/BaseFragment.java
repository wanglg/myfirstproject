package com.example.fragment;

import android.app.Activity;
import android.app.Fragment;

public class BaseFragment extends Fragment {
	Activity activity;
	@Override
	public void onAttach(Activity activity) {
		
		super.onAttach(activity);
		this.activity=activity;
	}

}
