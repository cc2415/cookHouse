package com.infzm.slidingmenu.demo.adapter.food;

import java.util.ArrayList;

import cn.czc.cookhouse.groable.MyGloable;

import com.infzm.slidingmenu.demo.R;
import com.lidroid.xutils.BitmapUtils;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;

public class ViewPagerAdapter extends PagerAdapter {
	ArrayList<String> ml;
	Context mContext;
	public ViewPagerAdapter(Context mContext,ArrayList<String> ml){
		this.mContext=mContext;
		this.ml=ml;
	}
	@Override
	public int getCount() {
		return 1;
	}

	@Override
	public boolean isViewFromObject(View arg0, Object arg1) {
		// TODO Auto-generated method stub
		return arg0==arg1;
	}
	
	@Override
	public Object instantiateItem(ViewGroup container, int position) {
		ImageView iv=new ImageView(mContext);
		iv.setScaleType(ScaleType.FIT_XY);
		iv.setImageResource(R.drawable.ic_launcher);
		BitmapUtils bitmapUtils=new BitmapUtils(mContext);
		bitmapUtils.display(iv, MyGloable.imge+ml.get(4));
		container.addView(iv);
		return iv;
	}
	@Override
	public void destroyItem(ViewGroup container, int position, Object object) {
		container.removeView((View)object);
	}

}
