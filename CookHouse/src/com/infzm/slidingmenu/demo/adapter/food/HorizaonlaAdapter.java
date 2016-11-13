package com.infzm.slidingmenu.demo.adapter.food;

import java.util.ArrayList;

import cn.czc.cookhouse.groable.MyGloable;

import com.infzm.slidingmenu.demo.R;
import com.infzm.slidingmenu.demo.bean.food.OtherRecipes.moreRecipes;
import com.lidroid.xutils.BitmapUtils;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class HorizaonlaAdapter extends BaseAdapter {
	Context mContext;
	ArrayList<moreRecipes> list;

	public HorizaonlaAdapter(Context mContext, ArrayList<moreRecipes> list) {
		this.mContext = mContext;
		this.list = list;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return list.size();
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
		// 要优化
		ViewHolder viewHolder = null;
		if (convertView == null) {
			convertView = LayoutInflater.from(mContext).inflate(
					R.layout.item_detail_gv, parent, false);
			viewHolder=new ViewHolder();
			viewHolder.iv = (ImageView) convertView
					.findViewById(R.id.iv_item_recipes_gv);
			viewHolder.tv_count=(TextView) convertView.findViewById(R.id.tv_item_recipes_count);
			viewHolder.tv_food=(TextView) convertView.findViewById(R.id.tv_item_recipes_food);
			convertView.setTag(viewHolder);
		}else{
			viewHolder=(ViewHolder) convertView.getTag();
		}
		BitmapUtils bitmapUtils=new BitmapUtils(mContext);
		bitmapUtils.display(viewHolder.iv, MyGloable.imge+list.get(position).img+"_200x200");
		viewHolder.tv_count.setText(list.get(position).count);
		viewHolder.tv_food.setText(list.get(position).food);
		return convertView;
	}

	public static class ViewHolder {
		ImageView iv;
		TextView tv_food;
		TextView tv_count;
	}

}
