package com.infzm.slidingmenu.demo.adapter.news;

import java.util.ArrayList;

import com.infzm.slidingmenu.demo.R;
import com.infzm.slidingmenu.demo.bean.news.InfoList.mInfolist;
import com.lidroid.xutils.BitmapUtils;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class ViewpageAdapter extends PagerAdapter {
	Context mContext;
	ArrayList<mInfolist> mList;
	public ViewpageAdapter(Context mContext,ArrayList<mInfolist> list){
		this.mContext=mContext;
		this.mList=list;
	}
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return 5;
	}

	@Override
	public boolean isViewFromObject(View arg0, Object arg1) {
		// TODO Auto-generated method stub
		return arg0==arg1;
	}
	@Override
	public void destroyItem(ViewGroup container, int position, Object object) {
		container.removeView((View)object);
	}
	@Override
	public Object instantiateItem(ViewGroup container, final int position) {
		View view=View.inflate(mContext, R.layout.item_news_pager,null);
		TextView tv=(TextView) view.findViewById(R.id.tv_news_vp);
		ImageView iv=(ImageView) view.findViewById(R.id.iv_news_vp);
		BitmapUtils bitmapUtils=new BitmapUtils(mContext);
		bitmapUtils.display(iv, "http://tnfs.tngou.net/image"+mList.get(position).img+"_620x400");
		tv.setText(mList.get(position).title);
//		
		
//		iv.setOnClickListener(new OnClickListener() {
//			@Override
//			public void onClick(View v) {
////				System.out.println(mList.get(position).title);
//			}
//		});
//		PagerFrame frame = new PagerFrame();
//		View view=frame.mRootView;
		container.addView(view);
		
		return view;
	}

}
