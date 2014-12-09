package com.example.widget;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.View;

public class WrapContentHeightViewPager extends ViewPager {

	public int multiple=2;
	public WrapContentHeightViewPager(Context context, AttributeSet attrs) {
		super(context, attrs);
	}
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		
		
		int height = 0;
	    //下面遍历所有child的高度
	    for (int i = 0; i < getChildCount(); i++) {
	      View child = getChildAt(i);
	      child.measure(widthMeasureSpec,
	          MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED));
	      int h = child.getMeasuredHeight();
	      if (h > height) //采用最大的view的高度。
	        height = h;
	    }

	    heightMeasureSpec = MeasureSpec.makeMeasureSpec(height*multiple,
	        MeasureSpec.EXACTLY);

	    super.onMeasure(widthMeasureSpec, heightMeasureSpec);
		/*super.onMeasure(widthMeasureSpec, heightMeasureSpec);

		// find the first child view
		View view = getChildAt(0);
		if (view != null) {
			// measure the first child view with the specified measure spec
			view.measure(widthMeasureSpec, heightMeasureSpec);
		}

		setMeasuredDimension(getMeasuredWidth(),
				measureHeight(heightMeasureSpec, view)*2);*/
	}
	
	   /**
     * Determines the height of this view
     *
     * @param measureSpec A measureSpec packed into an int
     * @param view the base view with already measured height
     *
     * @return The height of the view, honoring constraints from measureSpec
     */
    private int measureHeight(int measureSpec, View view) {
        int result = 0;
        int specMode = MeasureSpec.getMode(measureSpec);
        int specSize = MeasureSpec.getSize(measureSpec);

        if (specMode == MeasureSpec.EXACTLY) {
            result = specSize;
        } else {
            // set the height from the base view if available
            if (view != null) {
                result = view.getMeasuredHeight();
            }
            if (specMode == MeasureSpec.AT_MOST) {
                result = Math.min(result, specSize);
            }
        }
        return result;
    }
    
   

}
