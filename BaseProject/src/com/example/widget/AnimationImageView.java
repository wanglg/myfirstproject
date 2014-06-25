package com.example.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;


public class AnimationImageView extends ImageView  implements View.OnFocusChangeListener{

	ImageView shadowBackground;
	int shadowBackgroundRsId;
	ScaleAnimation zoomin;
	RectF mNewRectF, mOldRectF;
	
	/**图片放大倍数*/
	private final float zoomInRate=1.1f;
	
	private int bitmap_W;
	private int bitmap_H;
	public AnimationImageView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		//TypedArray ta = context.obtainStyledAttributes(attrs,R.styleable.AnimationImageView); 
		//shadowBackgroundRsId=ta.getInt(R.styleable.AnimationImageView_shadowbackground, 0);
		setOnFocusChangeListener(this);
	}

	public AnimationImageView(Context context, AttributeSet attrs) {
		super(context, attrs);
		//TypedArray ta = context.obtainStyledAttributes(attrs,R.styleable.AnimationImageView); 
		//shadowBackgroundRsId=ta.getResourceId(R.styleable.AnimationImageView_shadowbackground, 0);
		setOnFocusChangeListener(this);
	}

	public AnimationImageView(Context context) {
		super(context);
		setOnFocusChangeListener(this);
	}
	
	public void setShadowBackground(ImageView shadowBackground){
		this.shadowBackground=shadowBackground; 
		if (shadowBackgroundRsId!=0) {
			shadowBackground.setBackgroundResource(shadowBackgroundRsId);
		}
		
	}
	@Override
	public void setImageBitmap(Bitmap bm) {/*
		
		bitmap_W = bm.getWidth();
		bitmap_H = bm.getHeight();
		Bitmap imgTemp = Bitmap.createBitmap(bitmap_W, bitmap_H, Bitmap.Config.ARGB_8888);  
		Canvas canvas = new Canvas(imgTemp);  
		Paint paint = new Paint(); // 建立画笔  
        paint.setDither(true);  
        paint.setFilterBitmap(true);  
        Rect src = new Rect(0, 0, bitmap_W, bitmap_H);  
        Rect dst = new Rect(0, 0, bitmap_W, bitmap_H);  
        canvas.drawBitmap(bm, src, dst, paint);  
  
		
		Paint textPaint = new Paint(Paint.ANTI_ALIAS_FLAG
				| Paint.DEV_KERN_TEXT_FLAG);// 设置画笔
		textPaint.setTextSize(20.0f);// 字体大小
		textPaint.setTypeface(Typeface.DEFAULT_BOLD);// 采用默认的宽�?
		textPaint.setColor(Color.RED);// 采用的颜�?
		canvas.drawText("sad",bitmap_W /2-5, bitmap_H/2+5, textPaint);
		canvas.save(Canvas.ALL_SAVE_FLAG);
		canvas.restore();
		super.setImageBitmap(bm);
		
	*/}
	
	@Override
	protected void onDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		super.onDraw(canvas);
		//canvas.drawt
	}
	
	public void bindViewAndRectf(ImageView shadowBackground,RectF newRectf,RectF oldRectF){
		this.shadowBackground=shadowBackground;
		
		this.mNewRectF=newRectf;
		this.mOldRectF=oldRectF;
	}
	/**
	 * 失去焦点的的动画动作
	 * 
	 * @param paramInt
	 *                失去焦点的item
	 * */
	private void showLooseFocusAinimation() {
		
		this.startAnimation(this.createAnimation());
		
		/*if (shadowBackground!=null) {
			shadowBackground.startAnimation(alphaAnimation(0.0F, 1.0F, 150L, 0L));
			shadowBackground.setVisibility(View.GONE);
		}*/
	}
	
	/**
	 * 获得焦点的item的动画动�?
	 * 
	 * @param paramInt
	 *                获得焦点的item
	 * */
	public void showOnFocusAnimation() {
		this.bringToFront();
		
		if (zoomin == null) {
			zoomin = new ScaleAnimation(1.0f, zoomInRate, 1.0f, zoomInRate,
					Animation.RELATIVE_TO_SELF, 0.5f,
					Animation.RELATIVE_TO_SELF, 0.5f);
		}
		zoomin.setAnimationListener(new AnimationListener() {

			@Override
			public void onAnimationStart(Animation animation) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onAnimationRepeat(Animation animation) {
				// TODO Auto-generated method stub

			}
			@SuppressLint("NewApi")
			@Override
			public void onAnimationEnd(Animation animation) {
				// TODO Auto-generated method stub
				if (shadowBackground!=null) {/*
					int width=(int) (AnimationImageView.this.getWidth()*zoomInRate+20);
					int hight=(int) (AnimationImageView.this.getHeight()*zoomInRate+20);
					//mNewRectF.
					int widthDif=(int) (((zoomInRate-1)/2)*AnimationImageView.this.getWidth());
					int hightDif=(int) (((zoomInRate-1)/2)*AnimationImageView.this.getHeight());
					RelativeLayout.LayoutParams rl=new RelativeLayout.LayoutParams(width,hight);
					//int leftMargin=(int) (AnimationImageView.this.getX()-AnimationImageView.this.getWidth()*0.1);
					//int topMargin=(int) (AnimationImageView.this.getY()-AnimationImageView.this.getWidth()*0.1);
					rl.leftMargin=(int) (mNewRectF.left-10);
					rl.topMargin=(int) (mNewRectF.top-10);
					
					rl.leftMargin=(int) (AnimationImageView.this.getX()-180);
					rl.topMargin=(int) (AnimationImageView.this.getY());
					shadowBackground.setScaleType(ScaleType.FIT_XY);
					shadowBackground.setLayoutParams(rl);
					shadowBackground.setBackgroundResource(R.drawable.shadow_big_top);
					//shadowBackground.startAnimation(alphaAnimation(0.0F, 1.0F, 150L, 0L));
					shadowBackground.setVisibility(View.VISIBLE);
					//shadowBackground.bringToFront();
					
					
				*/}
			
			}
		});
		zoomin.setDuration(300);// 设置动画持续时间
		zoomin.setFillAfter(true);// 动画执行完后是否停留在执行完的状�?
        this.startAnimation(zoomin);
	}
	
	/**
	 * 淡入淡出动画
	 * @param paramFloat1
	 * @param paramFloat2
	 * @param paramLong1
	 * @param paramLong2
	 * @return
	 */
/*	public Animation alphaAnimation(float paramFloat1, float paramFloat2, long paramLong1, long paramLong2) {
		AlphaAnimation localAlphaAnimation = new AlphaAnimation(paramFloat1, paramFloat2);
		localAlphaAnimation.setDuration(paramLong1);
		localAlphaAnimation.setStartOffset(paramLong2);
		localAlphaAnimation.setInterpolator(new AccelerateInterpolator());
		return localAlphaAnimation;
	}*/
	/**
	 * 缩小动画
	 * @return
	 */
	public Animation createAnimation() {
		ScaleAnimation localScaleAnimation = new ScaleAnimation(zoomInRate,1.0f , zoomInRate, 1.0f,
				Animation.RELATIVE_TO_SELF, 0.5f,
				Animation.RELATIVE_TO_SELF, 0.5f);
		localScaleAnimation.setFillAfter(true);
		//localScaleAnimation.setInterpolator(new AccelerateInterpolator());
		localScaleAnimation.setDuration(300);
		return localScaleAnimation;
	}

	@SuppressLint("NewApi")
	@Override
	public void onFocusChange(View v, boolean hasFocus) {
		
		if (hasFocus) {
			double x=v.getLeft();
			double y=v.getTop();
			double width=v.getWidth();
			double height=v.getHeight();
			double bigwidth=width*zoomInRate;
			double bigheight=height*zoomInRate;
			x=x-(bigwidth-width)/2;
			y=y-(bigheight-height)/2;
			if (mNewRectF!=null) {
				mNewRectF.left = (float) x;
				mNewRectF.top = (float) y;
				mNewRectF.right = (float) (x + bigwidth);
				mNewRectF.bottom = (float) (y + bigheight);
			}
			
			//showFocusBoxViewAnimation(v);
			showOnFocusAnimation();
			//setFrame((int)mNewRectF.left,(int) mNewRectF.top,(int)mNewRectF.right, (int)mNewRectF.bottom);
		}else{
			if (mOldRectF!=null) {
				mOldRectF.left = v.getX();
				mOldRectF.top = v.getY();
				mOldRectF.right = v.getX() + v.getWidth();
				mOldRectF.bottom = v.getY() + v.getHeight();
			}
			
			
			
			
			if (shadowBackground!=null) {
				shadowBackground.setVisibility(View.INVISIBLE);
			}
			showLooseFocusAinimation();
		}
		
	}
	
	/**
	 * 展示飞框动画
	 * @param v
	 */
	/*private void showFocusBoxViewAnimation(View v){
		if (fox!=null) {
			fox.focus(mOldRectF, mNewRectF, v, null, 300);
		}
	}*/
}
