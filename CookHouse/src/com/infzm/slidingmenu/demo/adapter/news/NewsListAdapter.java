package com.infzm.slidingmenu.demo.adapter.news;

import java.util.ArrayList;

import com.infzm.slidingmenu.demo.R;
import com.infzm.slidingmenu.demo.bean.news.InfoList.mInfolist;
import com.lidroid.xutils.BitmapUtils;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class NewsListAdapter extends BaseAdapter {
	Context mContext;
	ArrayList<mInfolist> tngouList;
	public NewsListAdapter(Context mContext,ArrayList<mInfolist> tngouList){
		this.mContext=mContext;
		this.tngouList=tngouList;
	}
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return tngouList.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		mViewHold viewHold;
		if (convertView==null) {
			viewHold=new mViewHold();
			convertView=LayoutInflater.from(mContext).inflate(R.layout.item_news_newslist, parent, false);
			viewHold.iv=(ImageView) convertView.findViewById(R.id.iv_newslist);
			viewHold.title=(TextView) convertView.findViewById(R.id.tv_newslist_title);
			viewHold.count=(TextView) convertView.findViewById(R.id.tv_newslist_count);
			convertView.setTag(viewHold);
		}else {
			viewHold=(mViewHold) convertView.getTag();
		}
		viewHold.title.setText(tngouList.get(position).title);
		viewHold.count.setText(tngouList.get(position).count);
		BitmapUtils fd=new BitmapUtils(mContext);
		fd.display(viewHold.iv, "http://tnfs.tngou.net/image"+tngouList.get(position).img+"_120x120");
		return convertView;
	}
	public static class mViewHold{
		ImageView iv;
		TextView title;
		TextView count;
		
	}

}
