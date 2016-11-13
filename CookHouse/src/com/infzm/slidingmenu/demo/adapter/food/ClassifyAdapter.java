package com.infzm.slidingmenu.demo.adapter.food;

import java.util.ArrayList;

import com.infzm.slidingmenu.demo.R;
import com.infzm.slidingmenu.demo.bean.food.Classify.detail_tngou;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class ClassifyAdapter extends BaseAdapter {
	ArrayList<detail_tngou> tngous;
	Context mContext;
	public ClassifyAdapter(ArrayList<detail_tngou> tngous,Context mContext){
		this.tngous=tngous;
		this.mContext=mContext;
		
	}
	@Override
	public int getCount() {
		return tngous.size();
	}

	@Override
	public Object getItem(int position) {
		return null;
	}

	@Override
	public long getItemId(int position) {
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
//		优化  return的是convertView
		ViewHolder viewHolder=null;
		if (convertView==null) {
			viewHolder=new ViewHolder();
			convertView = LayoutInflater.from(mContext).inflate(R.layout.item_classify,parent,false);
			viewHolder.classifyName=(TextView) convertView.findViewById(R.id.tv_classifyName);
			convertView.setTag(viewHolder);
		}else{
			viewHolder=(ViewHolder) convertView.getTag();
		}
		viewHolder.classifyName.setText(tngous.get(position).name);
		
//		View view = View.inflate(mContext, R.layout.classify_item, null);
//		TextView tv=(TextView) view.findViewById(R.id.tv_classifyName);
//		tv.setText(tngous.get(position).name);
		return convertView;
	}
	public static class ViewHolder{
		TextView classifyName;
	}

}
