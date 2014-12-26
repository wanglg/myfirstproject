package com.example.fragment;

import com.example.baseproject.R;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MusicFragment extends BaseFragment {
	public static MusicFragment getInstance() {
		return new MusicFragment();
	}
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		MyText textView = new MyText(getActivity());  
        textView.setText(getActivity().getResources().getString(R.string.string_format, "leo"));  
        textView.setGravity(Gravity.CENTER_HORIZONTAL);  
        LinearLayout layout = new LinearLayout(getActivity());  
        LayoutParams params = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);  
        layout.addView(textView, params);  
        textView.setOnTouchListener(new View.OnTouchListener() {
			
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				Log.i("wang", "onTouch--->"+(event.getAction()==MotionEvent.ACTION_DOWN));
				return false;
			}
		});
        /*textView.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
			}
		});*/
        return layout;  }
	
	
	public class MyText extends TextView{

		public MyText(Context context) {
			super(context);
			
		}
		
		@Override
		public boolean onTouchEvent(MotionEvent event) {
			
			boolean result= super.onTouchEvent(event);
			
			return result;
		}
		
	}
}
