package com.example.widget;

import java.util.ArrayList;
import java.util.List;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.baseproject.R;
import com.example.bean.SudokuMenuItem;

public class SudokuDialog extends Dialog {

	private int pageNum = 6;
	private int gridNum = 0;
	private int numColumns=3;
	private int defaultPage=0;
	List<SudokuMenuItem> mList;
	

	List<View> viewList;
	LayoutInflater inflater;
	private SudokuItemClickListener itemClickListener;

	private Context mContext;
	public SudokuDialog(Context context) {
		super(context, R.style.SudokuTheme);
	}

	public SudokuDialog(Context context, List<SudokuMenuItem> mList) {
		this(context);
		this.mList = mList;
		if (mList != null && !mList.isEmpty()) {
			gridNum = mList.size() / (pageNum+1) + 1;
		}
		this.mContext=context;
		inflater = (LayoutInflater) getContext().getSystemService(
				Context.LAYOUT_INFLATER_SERVICE);
	}

	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		View rootView = inflater.inflate(R.layout.sudoku_layout, null);
		WrapContentHeightViewPager viewPager = (WrapContentHeightViewPager) rootView.findViewById(R.id.sudoku_viewpager);
		final LinearLayout sudoku_dots = (LinearLayout) rootView.findViewById(R.id.sudoku_dots);
		
		
		viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
			
			@Override
			public void onPageSelected(int arg0) {
				ImageView imageView=(ImageView) sudoku_dots.getChildAt(arg0);
				if (imageView!=null) {
					sudoku_dots.dispatchSetSelected(false);
					imageView.setSelected(true);
				}
			}
			
			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
			}
			
			@Override
			public void onPageScrollStateChanged(int arg0) {
			}
		});
		initViewPagerItemView();
		
        	for (int i = 0; i < gridNum; i++) {
    			Drawable drawable=mContext.getResources().getDrawable(R.drawable.sudoku_dots_selector);

    			ImageView imageView=new ImageView(getContext());
    			
    			imageView.setImageDrawable(drawable);
    			LinearLayout.LayoutParams layoutParams=new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
    					LinearLayout.LayoutParams.WRAP_CONTENT);
    			sudoku_dots.addView(imageView, layoutParams);
    		}
        
		
		if (viewList!=null) {
			viewPager.setAdapter(new SudokuPageAdapter());
			int multiple=pageNum/numColumns;
			multiple= pageNum% numColumns==0? multiple:multiple+1;
			viewPager.multiple=multiple;
			if (defaultPage!=0) {
				viewPager.setCurrentItem(defaultPage);
			}
			sudoku_dots.getChildAt(defaultPage).setSelected(true);
			if(gridNum==1){
				sudoku_dots.setVisibility(View.GONE);
			}
		}
		
		setContentView(rootView);
	}

	private void initViewPagerItemView() {
		viewList = new ArrayList<View>();
		for (int i = 0; i < gridNum; i++) {
			View view=inflater.inflate(R.layout.sudoku_viewpager_item, null);
			GridView gridView=(GridView) view.findViewById(R.id.sudoku_grid);
			gridView.setNumColumns(numColumns);
			//gridView.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, width_height*2));
			final List<SudokuMenuItem> items=new ArrayList<SudokuMenuItem>();
			for (int j = pageNum*i; j < pageNum*(i+1) && j < mList.size(); j++) {
				items.add(mList.get(j));
			}
			gridView.setAdapter(new GridAdapter(items));
			gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

				@Override
				public void onItemClick(AdapterView<?> parent, View view,int position, long id) {
					
					if (itemClickListener!=null) {
						itemClickListener.itemClick(items.get(position));
					}
				}
			});
			viewList.add(view);
		}
	}

	

	private class SudokuPageAdapter extends PagerAdapter {

		@Override
		public int getCount() {
			return viewList.size();
		}

		@Override
		public boolean isViewFromObject(View arg0, Object arg1) {
			return arg0==arg1;
		}

		@Override
		public void destroyItem(ViewGroup container, int position, Object object) {
			container.removeView(viewList.get(position));
		}
		@Override
		public Object instantiateItem(ViewGroup container, int position) {
			container.addView(viewList.get(position), 0);

			return viewList.get(position);
		}
		
	}
	
	public int getPageNum() {
		return pageNum;
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}

	public int getNumColumns() {
		return numColumns;
	}

	public void setNumColumns(int numColumns) {
		this.numColumns = numColumns;
	}
	public int getDefaultPage() {
		return defaultPage;
	}

	public void setDefaultPage(int defaultPage) {
		this.defaultPage = defaultPage;
	}

	
	  class GridAdapter extends BaseAdapter{

		List<SudokuMenuItem> mList;
		GridAdapter(List<SudokuMenuItem> mList){
			this.mList=mList;
		}
		@Override
		public int getCount() {
			return mList==null ? 0:mList.size();
		}

		@Override
		public Object getItem(int position) {
			return mList.get(position);
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			if (convertView == null) {
				convertView = inflater.inflate(R.layout.sudoku_grid_item, null);

			}
			
			ImageView imageView = (ImageView) convertView
					.findViewById(R.id.gridview_item_image);
			TextView textView = (TextView) convertView
					.findViewById(R.id.gridview_item_text);
			SudokuMenuItem item = mList.get(position);
			imageView.setImageDrawable(item.drawableIcon);
			textView.setText(item.title);

			/*ImageView imageView2=new ImageView(getContext());
			imageView2.setBackgroundColor(Color.BLUE);
			RelativeLayout.LayoutParams layoutParams=new RelativeLayout.LayoutParams(1, 1000);
			layoutParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
			imageView2.setLayoutParams(layoutParams);
			((ViewGroup)convertView).addView(imageView2);*/
			return convertView;
		}
		
	}
	  
	  
	interface SudokuItemClickListener{
		
		void itemClick(SudokuMenuItem item);
	}


	

}
