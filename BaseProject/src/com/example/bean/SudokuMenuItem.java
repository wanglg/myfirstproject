package com.example.bean;

import android.graphics.drawable.Drawable;
import android.view.MenuItem;


public class SudokuMenuItem  {

	public CharSequence title;
	public int id;
	public boolean mIsVisible = true;
	public boolean hasSubItems;
	public SudokuMenuItem [] subItems;
	public Drawable drawableIcon;
	
	public SudokuMenuItem(int id, Drawable drawableIcon, CharSequence title){
		this.id = id;
		this.title = title;
		this.drawableIcon = drawableIcon;
	}
	
	
	public SudokuMenuItem(MenuItem menuItem){
		initData(menuItem);
		
	}
	
	private void initData(MenuItem menuItem) {
		id=menuItem.getItemId();
		drawableIcon=menuItem.getIcon();
		title=menuItem.getTitle();
		mIsVisible=menuItem.isVisible();
	}

	public boolean isVisible(){
		return mIsVisible;
	}
	
	public void setVisible(boolean visible){
		mIsVisible = visible;
	}
	
	public boolean hasSubItems(){
		return hasSubItems;
	}
	
	public void setSubItems(SudokuMenuItem [] items){
		hasSubItems = true;
		subItems = items;
	}
	
	public SudokuMenuItem [] getSubMenu(){
		return subItems;
	}
	
	
	
}
