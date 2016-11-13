package com.infzm.slidingmenu.demo.fragment;

import java.util.ArrayList;

import cn.czc.cookhouse.groable.MyGloable;

import com.google.gson.Gson;
import com.infzm.slidingmenu.demo.R;
import com.infzm.slidingmenu.demo.adapter.news.NewsAdapter;
import com.infzm.slidingmenu.demo.bean.news.InfonClassify;
import com.infzm.slidingmenu.demo.bean.news.InfonClassify.NewsClassify;
import com.infzm.slidingmenu.demo.view.HorizontalViewPager;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;

import android.R.color;
import android.R.integer;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.HorizontalScrollView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * 
 * @author wuwenjie
 * @description 今日
 */
public class MyFavoritesFragment extends Fragment {

	private ArrayList<NewsClassify> tngou;
	private HorizontalViewPager vPager;
//	private ViewPager vPager;
	private HorizontalScrollView hsv;
	private LinearLayout ll;
	private int screeWidth;

	// private TextView textView;
	// private ViewPager pager;
	// private TabPageIndicator indicator;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.frag_news, null);
		initView(view);
		initInfoData();
		//获得屏幕大小
		DisplayMetrics metrics = new DisplayMetrics();
		getActivity().getWindowManager().getDefaultDisplay().getMetrics(metrics);
		screeWidth = metrics.widthPixels;
		
		vPager.setOnPageChangeListener(new OnPageChangeListener() {
			
			@Override
			public void onPageSelected(int arg0) {
				for (int i = 0; i < tngou.size(); i++) {
					if (i!=arg0) {
						TextView tt=(TextView) ll.getChildAt(i);
						tt.setTextColor(Color.BLACK);
					}
				}
				TextView tv=(TextView) ll.getChildAt(arg0);
				int left=tv.getLeft();
				int width=tv.getMeasuredWidth();
				int len=left+width/2-screeWidth/2;
				LayoutParams pr = titleParams();
				pr.leftMargin=len;
				tv.setTextColor(Color.RED);
				hsv.smoothScrollTo(len, 0);
			}
			
			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
				
			}
			
			@Override
			public void onPageScrollStateChanged(int arg0) {
				
			}
		});
		
		
		return view;
	}

	public void initView(View view) {
		hsv = (HorizontalScrollView) view.findViewById(R.id.hsv_news);
		ll = (LinearLayout) view.findViewById(R.id.ll_news);
		vPager = (HorizontalViewPager) view.findViewById(R.id.vp_news);
	}
	//	取得健康资讯分类，可以通过分类id取得资讯列表
	public void initInfoData() {
		String url = "http://www.tngou.net/api/info/classify";
		RequestParams params = new RequestParams() ;
		params.addHeader("apikey", MyGloable.apiKey);
		HttpUtils httpUtils = new HttpUtils();
		httpUtils.send(HttpMethod.GET, url, params,
				new RequestCallBack<String>() {

					@Override
					public void onSuccess(ResponseInfo<String> responseInfo) {
						// System.out.println(responseInfo.result);
						// textView.setText(responseInfo.result);
						Gson gson = new Gson();
						InfonClassify fromJson = gson.fromJson(
								responseInfo.result, InfonClassify.class);
						tngou = fromJson.tngou;
//						System.out.println(tngou.size() + "");
						for (int i = 0; i < tngou.size(); i++) {
							final TextView tv=new TextView(getActivity());
							tv.setText(tngou.get(i).title);
							tv.setTextSize(25);
							Drawable drawable = getResources().getDrawable(R.drawable.title);
							drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight()); //设置边界
							tv.setCompoundDrawables(drawable, null, null, null);
							ll.addView(tv,titleParams());
							
						}
						TextView t = (TextView) ll.getChildAt(0);
						t.setTextColor(Color.RED);
						vPager.setAdapter(new NewsAdapter(tngou, getActivity()));
						
					}

					@Override
					public void onFailure(HttpException error, String msg) {
						Toast.makeText(getActivity(), "网络连接错误", 1).show();
					}
				});
	}
	
	public void initDetailInfolist(){
		
	}
	public LayoutParams titleParams(){
		LayoutParams p=new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		p.leftMargin=25;
		return p;
	}
}
