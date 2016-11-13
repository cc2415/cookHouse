package com.infzm.slidingmenu.demo.adapter.food;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.util.ArrayList;

import cn.czc.cookhouse.groable.MyGloable;

import com.infzm.slidingmenu.demo.R;
import com.infzm.slidingmenu.demo.bean.food.Recipes.detail_Recipes;
import com.lidroid.xutils.BitmapUtils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class RecipesAdapter extends BaseAdapter {
	ArrayList<detail_Recipes> tngou;
	Context mContext;
	private BitmapUtils bitmapUtils;

	public RecipesAdapter(ArrayList<detail_Recipes> tngou, Context mContext) {
		this.tngou = tngou;
		this.mContext = mContext;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return tngou.size();
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
		// 一定要优化
		ViewHolder viewHolder = null;
		if (convertView == null) {
			convertView = LayoutInflater.from(mContext).inflate(
					R.layout.item_recipes, parent, false);
			viewHolder = new ViewHolder();
			viewHolder.img = (ImageView) convertView.findViewById(R.id.iv_imag);
			viewHolder.name = (TextView) convertView
					.findViewById(R.id.tv_recipesName);
			viewHolder.bef_food = (TextView) convertView
					.findViewById(R.id.tv_bef_foodName);
			viewHolder.bef_description = (TextView) convertView
					.findViewById(R.id.tv_bef_decription);
			viewHolder.bef_count = (TextView) convertView
					.findViewById(R.id.tv_bef_count);
			viewHolder.food = (TextView) convertView
					.findViewById(R.id.tv_foodName);
			viewHolder.description = (TextView) convertView
					.findViewById(R.id.tv_decription);
			viewHolder.count = (TextView) convertView
					.findViewById(R.id.tv_count);
			convertView.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) convertView.getTag();
		}
		bitmapUtils = new BitmapUtils(mContext);
		bitmapUtils.display(viewHolder.img, MyGloable.imge+tngou.get(position).img+"_80x80");
		viewHolder.name.setText(tngou.get(position).name);
		viewHolder.description.setText(tngou.get(position).description);
		viewHolder.food.setText(tngou.get(position).food);
		viewHolder.count.setText(tngou.get(position).count);
		return convertView;
	}

	/**
     * 获取网落图片资源 
     * @param url
     * @return
     */
    public static Bitmap getHttpBitmap(String url){
        URL myFileURL;
        Bitmap bitmap=null;
        try{
            myFileURL = new URL(url);
            //获得连接
            HttpURLConnection conn=(HttpURLConnection)myFileURL.openConnection();
            //设置超时时间为6000毫秒，conn.setConnectionTiem(0);表示没有时间限制
            conn.setConnectTimeout(6000);
            //连接设置获得数据流
            conn.setDoInput(true);
            //不使用缓存
            conn.setUseCaches(false);
            //这句可有可无，没有影响
            //conn.connect();
            //得到数据流
            InputStream is = conn.getInputStream();
            //解析得到图片
            bitmap = BitmapFactory.decodeStream(is);
            //关闭数据流
            is.close();
        }catch(Exception e){
            e.printStackTrace();
        }
         
        return bitmap;
         
    }
	
	
	public static class ViewHolder {
		public TextView name;
		public TextView description;
		public TextView bef_description;
		public TextView count;
		public TextView bef_count;
		public TextView food;
		public TextView bef_food;
		public ImageView img;
	}

}
